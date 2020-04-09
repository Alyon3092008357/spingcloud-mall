package com.alyon.mall.auth.contants;

public enum HeaderContants {
    /**
     * 用户信息头
     */
    USER_HEADER("x-user-header"),

    /**
     * 用户id信息头
     */
    USER_ID_HEADER("x-userid-header"),

    /**
     * 角色信息头
     */
    ROLE_HEADER("x-role-header"),

    /**
     * 租户信息头(应用)
     */
    TENANT_HEADER("x-tenant-header");

    private String header;
    HeaderContants(String header){
        this.header = header;
    }
    public String getHeader(){
        return  this.header;
    }
}
