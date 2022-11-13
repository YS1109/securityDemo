package com.ysoztf.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.ysoztf.security.mapper")
public class SecurityApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityApplication.class, args);
        /**
         * run.getBean(DefaultSecurityFilterChain.class)
         * 0 = {WebAsyncManagerIntegrationFilter@8995}
         * 1 = {SecurityContextPersistenceFilter@8996}
         * 2 = {HeaderWriterFilter@8997}
         * 3 = {LogoutFilter@8998}
         * 4 = {CorsFilter@8999}
         * 5 = {UsernamePasswordAuthenticationFilter@9000}
         * 6 = {DefaultLoginPageGeneratingFilter@9001}
         * 7 = {DefaultLogoutPageGeneratingFilter@9002}
         * 8 = {RequestCacheAwareFilter@9003}
         * 9 = {SecurityContextHolderAwareRequestFilter@9004}
         * 10 = {AnonymousAuthenticationFilter@9005}
         * 11 = {ExceptionTranslationFilter@9006}
         * 12 = {FilterSecurityInterceptor@9007}
         */
        System.out.println(1111);
    }

}
