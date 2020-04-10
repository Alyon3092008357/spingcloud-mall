package com.alyon.mall.core.auth.store;

import com.alyon.mall.core.auth.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * @ClassName AuthRedisTokenStore
 * @Description: 认证服务器,使用Redis存取令牌
 * @Author liang
 * @Date 2020/3/14
 * @Version V1.0
 **/
@ConditionalOnProperty(prefix = "mall.oauth2.token.store", name = "type", havingValue = "redis", matchIfMissing = true)
public class AuthRedisTokenStore {
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public TokenStore tokenStore() {
        return new CustomRedisTokenStore(connectionFactory, securityProperties);
    }
}
