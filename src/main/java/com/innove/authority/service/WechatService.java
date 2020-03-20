package com.innove.authority.service;

import com.innove.authority.bean.dto.request.WechatLoginRequest;
import com.innove.authority.bean.dto.response.Response;

public interface WechatService {

    Response getWechatID(String code,String enterCode);

    Response wechatLogin(WechatLoginRequest request) throws Exception;

    Response getWechatToken(String enterCode);
}
