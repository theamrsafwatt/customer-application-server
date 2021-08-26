package com.jumia.okta;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt();
        
        Okta.configureResourceServer401ResponseBody(http);
    }
}