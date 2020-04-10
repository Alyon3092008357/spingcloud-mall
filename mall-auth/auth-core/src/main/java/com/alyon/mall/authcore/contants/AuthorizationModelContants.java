package com.alyon.mall.core.auth.contants;

public enum AuthorizationModelContants {
    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password");
    private String code;
    AuthorizationModelContants(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
}
