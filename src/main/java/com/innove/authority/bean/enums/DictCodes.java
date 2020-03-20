package com.innove.authority.bean.enums;

public enum DictCodes {

    LOGIN_TYPE_PSW("password"),
    LOGIN_TYPE_NO_PSW("nopassword")
    ;

    private String type;

    DictCodes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
