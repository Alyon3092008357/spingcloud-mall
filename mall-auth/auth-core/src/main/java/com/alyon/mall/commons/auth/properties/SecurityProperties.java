package com.alyon.mall.auth.properties;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ClassName SecurityProperties
 * @Description: TODO
 * @Author liang
 * @Date 2020/3/14
 * @Version V1.0
 **/

@Setter
@Getter
@ConfigurationProperties(prefix = "demo.security")
@RefreshScope
public class SecurityProperties {
    private AuthProperties auth = new AuthProperties();

    private PermitProperties ignore = new PermitProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

}
