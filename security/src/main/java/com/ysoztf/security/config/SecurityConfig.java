package com.ysoztf.security.config;

import com.ysoztf.security.filter.CorsFilter;
import com.ysoztf.security.filter.security.SecurityTokenFilter;
import com.ysoztf.security.service.seurity.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig{
    private final CorsFilter corsFilter;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final PasswordEncoder passwordEncoder;
    private final SecurityTokenFilter securityTokenFilter;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          CorsFilter corsFilter,
                          AuthenticationSuccessHandler authenticationSuccessHandler,
                          AuthenticationFailureHandler authenticationFailureHandler,
                          AuthenticationEntryPoint authenticationEntryPoint,
                          SecurityTokenFilter securityTokenFilter) {
        this.corsFilter = corsFilter;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        //初始化编码格式和用户检索方法
        daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        //新版本security需要使用这种方法生成passwordEncoder, 使用此encoder生成的密文会带一个{'加密方式'}的前缀，例如{bcrypt}
        //因此如果想要自定义非默认的密码加密方式, 在密码存储时需要加上{'加密方式'}的前缀
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.passwordEncoder = passwordEncoder;
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        this.securityTokenFilter = securityTokenFilter;
    }

    /**
     * 相关配置
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .anyRequest().authenticated()
                )
                // 解决不允许显示在iframe的问题
                .headers().frameOptions().disable().cacheControl()
                .and()
                .and()
                .formLogin().loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(securityTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                //关闭session，使用sessionManagement().disable()方法是无效的，使用SessionCreationPolicy.NEVER仍会使用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(daoAuthenticationProvider)
                .build();
    }

    /**
     * 将使用的密码加密方式放入到容器中
     * @return
     */
    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return passwordEncoder;
    }
}
