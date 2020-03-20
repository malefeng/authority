package com.innove.authority.config.shiro;

import com.innove.authority.bean.enums.DictCodes;
import com.innove.authority.util.StringUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public MyHashedCredentialsMatcher() {
    }

    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUsernamePasswordToken uToken = (MyUsernamePasswordToken) token;
        if (uToken.getLoginType().equals(DictCodes.LOGIN_TYPE_PSW.getType())) {//账号密码登陆
            Object tokenHashedCredentials = null;
            try {
                tokenHashedCredentials = StringUtil.MD5(new String((char[]) token.getCredentials()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.equals(tokenHashedCredentials, info.getCredentials());
        } else {//免密登陆
            return true;
        }
    }
}
