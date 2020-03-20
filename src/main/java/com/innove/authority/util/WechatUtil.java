package com.innove.authority.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.EnterWechatEntity;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.dao.mapper.EnterWehatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class WechatUtil {

    @Value("${pproperty.wechat.getUserInfoUrl}")
    private String wechatGetUserInfoUrl;
    @Value("${pproperty.wechat.getAccessTokenUrl}")
    private String wechatGetAccessTokenUrl;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private EnterWehatDao enterWehatDao;

    private final String ENTER_KEY="wechat:enter:";
    private final String ACCESS_TOKEN = "wechat:access_token:";



    /**
     * 获取企业微信链接信息
     * @param enterCode
     * @return
     */
    public Response getEnterInfo(String enterCode){
        if(StringUtil.isBlank(enterCode)){
            return new Response().error(RuntimeErrorEnum.WECHAT_ENTER_CODE_INVALID);
        }
        EnterWechatEntity enterWechatEntity = (EnterWechatEntity) redisUtils.get(ENTER_KEY+enterCode);
        if(enterWechatEntity==null){
            enterWechatEntity = enterWehatDao.selectByEnterCode(enterCode);
        }
        if(enterWechatEntity==null){
            return new Response().error(RuntimeErrorEnum.WECHAT_ENTER_CODE_INVALID);
        }
        redisUtils.set(ENTER_KEY+enterCode,enterWechatEntity);
        return new Response(enterWechatEntity);
    }

    /**
     * 获取token信息
     * @param enterCode
     * @return
     */
    public Response getAccessToken(String enterCode){
        //获取企业微信链接信息
        Response resEnter = getEnterInfo(enterCode);
        if(resEnter.getCode()!=RuntimeErrorEnum.SUCCESS.getCode()){
            return resEnter;
        }
        EnterWechatEntity enterWechatEntity = (EnterWechatEntity) resEnter.getData();
        //从缓存获取token
        Object accessToken = redisUtils.get(ACCESS_TOKEN+enterCode);
        if(accessToken!=null){
            return new Response(String.valueOf(accessToken));
        }
        //从企业微信获取token
        LocalDateTime now = LocalDateTime.now();
        EnterWechatEntity finalEnterWechatEntity = enterWechatEntity;
        Map paramMap = new HashMap(){{
            put("corpid", finalEnterWechatEntity.getCorpId());
            put("corpsecret", finalEnterWechatEntity.getCorpSecret());
        }};
        String resStr = HttpClientUtil.doGet(wechatGetAccessTokenUrl,paramMap);
        if(StringUtil.isBlank(resStr)){
            return new Response().error(RuntimeErrorEnum.WECHAT_SERVER_ERROR);
        }
        JSONObject res = JSON.parseObject(resStr);
        if(res == null){
            return new Response().error(RuntimeErrorEnum.WECHAT_RESPONSE_ERROR);
        }
        Object errcode = res.get("errcode");
        Object newAccessToken = res.get("access_token");
        int expires_in = (int) res.get("expires_in");
        if(errcode==null||!StringUtil.equals("0",String.valueOf(errcode))
                ||newAccessToken == null
                || expires_in ==0 ){
            return new Response().error(RuntimeErrorEnum.WECHAT_GET_ID_ERROR);
        }
        //更新缓存
        LocalDateTime newExpires = now.plusSeconds((int) expires_in);
        redisUtils.set(ACCESS_TOKEN,newAccessToken,expires_in);
        return new Response(String.valueOf(newAccessToken));
    }

    /**
     * 获取微信用户id
     * @param code
     * @param enterCode
     * @return
     */
    public Response getWechatID(String code,String enterCode) {
        if(StringUtil.isBlank(enterCode)){
            return new Response().error(RuntimeErrorEnum.WECHAT_ENTER_CODE_INVALID);
        }
        Response accessToken = getAccessToken(enterCode);
        if(accessToken.getCode()!=RuntimeErrorEnum.SUCCESS.getCode()){
            return accessToken;
        }
        Map paramMap = new HashMap(){{
            put("code",code);
            put("access_token",accessToken.getData());
        }};
        String resStr = HttpClientUtil.doGet(wechatGetUserInfoUrl,paramMap);
        if(StringUtil.isBlank(resStr)){
            return new Response().error(RuntimeErrorEnum.WECHAT_SERVER_ERROR);
        }
        JSONObject res = JSON.parseObject(resStr);
        if(res == null){
            return new Response().error(RuntimeErrorEnum.WECHAT_RESPONSE_ERROR);
        }
        Object errcode = res.get("errcode");
        Object wechatId = res.get("UserId");
        if(errcode==null||!StringUtil.equals("0",String.valueOf(errcode))
                ||wechatId == null){
            return new Response().error(RuntimeErrorEnum.WECHAT_GET_ID_ERROR);
        }
        return new Response(String.valueOf(wechatId));
    }
}
