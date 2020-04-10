package com.alyon.mall.core.auth.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


/**
 * @ClassName AuthDbTokenStore
 * @Description: 认证服务器,使用数据库存取令牌
 * @Author liang
 * @Date 2020/3/14
 * @Version V1.0
 **/

@ConditionalOnProperty(prefix = "mall.oauth2.token.store", name = "type", havingValue = "db")
public class AuthDbTokenStore {
    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}
