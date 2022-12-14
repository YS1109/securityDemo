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
        //??????????????????????????????????????????
        daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        //?????????security??????????????????????????????passwordEncoder, ?????????encoder???????????????????????????{'????????????'}??????????????????{bcrypt}
        //?????????????????????????????????????????????????????????, ??????????????????????????????{'????????????'}?????????
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.passwordEncoder = passwordEncoder;
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        this.securityTokenFilter = securityTokenFilter;
    }

    /**
     * ????????????
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
                // ????????????????????????iframe?????????
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
                //??????session?????????sessionManagement().disable()???????????????????????????SessionCreationPolicy.NEVER????????????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(daoAuthenticationProvider)
                .build();
    }

    /**
     * ????????????????????????????????????????????????
     * @return
     */
    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return passwordEncoder;
    }
}
