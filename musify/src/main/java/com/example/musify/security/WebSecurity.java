package com.example.musify.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private JWTBlacklist blacklist;

    public WebSecurity(JWTBlacklist blacklist) {
        super();
        this.blacklist = blacklist;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/**/swagger-resources", "/**/swagger-resources/**", "/**/swagger-ui",
                        "/**/swagger-ui/**", "/**/swagger-ui.html", "/**/swagger-ui.html/**", "/**/v3/api-docs/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/register", "/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), blacklist))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
