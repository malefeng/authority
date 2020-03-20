package com.innove.authority.bean.enums;

public enum ConstantCodeEnum {

    CURRENT_USER("currentUser","当前登陆用户"),
    CLIENT_KIND("client_kind","终端类型：1-web端，2-企业微信");

    private String code;

    private String remark;

    ConstantCodeEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
