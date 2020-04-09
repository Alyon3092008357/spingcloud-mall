package com.alyon.mall.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;

/**
 * @ClassName ResourceServerConfig
 * @Description: TODO
 * @Author liang
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private PermissionAuthManager permissionAuthManager;

    //认证处理器
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        JsonAuthenticationEntryPoint entryPoint = new JsonAuthenticationEntryPoint();
        //token转换器
        ServerBearerTokenAuthenticationConverter tokenAuthenticationConverter = new ServerBearerTokenAuthenticationConverter();
        tokenAuthenticationConverter.setAllowUriQueryParameter(true);
        //oauth2认证过滤器
        AuthenticationWebFilter oauth2Filter = new AuthenticationWebFilter(customAuthenticationManager);
        oauth2Filter.setServerAuthenticationConverter(tokenAuthenticationConverter);
        oauth2Filter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        oauth2Filter.setAuthenticationSuccessHandler(new Oauth2AuthSuccessHandler());
        http.addFilterAt(oauth2Filter, SecurityWebFiltersOrder.AUTHENTICATION);

        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();
        if (securityProperties.getAuth().getHttpUrls().length > 0) {
            authorizeExchange.pathMatchers(securityProperties.getAuth().getHttpUrls()).authenticated();
        }
        if (securityProperties.getIgnore().getUrls().length > 0) {
            authorizeExchange.pathMatchers(securityProperties.getIgnore().getUrls()).permitAll();
        }
        authorizeExchange
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange()
                .access(permissionAuthManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new JsonAccessDeniedHandler())
                .authenticationEntryPoint(entryPoint)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .httpBasic().disable()
                .csrf().disable();
        return http.build();
    }
}
