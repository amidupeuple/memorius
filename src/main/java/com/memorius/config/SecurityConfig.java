package com.memorius.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemory authentication
        auth.inMemoryAuthentication()
                .withUser("user").password("1").roles("USER").and()
                .withUser("admin").password("1").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //I've added this block of code to enable transferring of cyrillic words from ui to this spring backend
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

        http
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                //.anyRequest().authenticated();
                .anyRequest().permitAll();
    }
}
