package com.alyon.mall.auth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ValidateCodeProperties
 * @Description: TODO
 * @Author liang
 * @Date 2020/3/14
 * @Version V1.0
 **/

@Setter
@Getter
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
