package com.innove.authority.service.impl;

import com.innove.authority.bean.dto.request.WechatLoginRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.enums.ConstantCodeEnum;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.config.shiro.MyUsernamePasswordToken;
import com.innove.authority.dao.mapper.UserDao;
import com.innove.authority.service.WechatService;
import com.innove.authority.util.StringUtil;
import com.innove.authority.util.WechatUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserDao dao;

    @Autowired
    private WechatUtil wechatUtil;

    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public Response getWechatID(String code,String enterCode) {
        return wechatUtil.getWechatID(code,enterCode);
    }

    @Override
    public Response getWechatToken(String enterCode) {
        return wechatUtil.getAccessToken(enterCode);
    }

    @Override
    public Response wechatLogin(WechatLoginRequest request) throws Exception {
        Response response = new Response();
        Response res = wechatUtil.getWechatID(request.getCode(),request.getEnterCode());
        if(res.getCode()!=RuntimeErrorEnum.SUCCESS.getCode()){
            return res;
        }
        String wechatId = (String) res.getData();
        if(StringUtil.isBlank(wechatId)){
            return response.error(RuntimeErrorEnum.WECHAT_SERVER_ERROR);
        }
        UserDetailResponse userEntity = dao.getByWechatCode(wechatId,2);
        if(userEntity==null){
            return response.error(RuntimeErrorEnum.NAME_NOT_NULL);
        }
        if(userEntity.getCanLogin()!=1){
            return response.error(RuntimeErrorEnum.USER_CANNOT_LOGIN);
        }
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(userEntity.getUserCode());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        String jseesionId = (String) subject.getSession().getId();
        userEntity.setToken(jseesionId);
        response.setData(userEntity);
        //当前用户信息缓存到session中
        httpServletRequest.getSession().setAttribute(ConstantCodeEnum.CURRENT_USER.getCode(), userEntity);
        httpServletRequest.getSession().setAttribute(ConstantCodeEnum.CLIENT_KIND.getCode(), 2);
        return response.success(RuntimeErrorEnum.SUCCESS.getCode(),"登陆成功");
    }
}
