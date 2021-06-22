package uz.pdp.apppcmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("superAdmin").password(passwordEncoder().encode("s123")).authorities(
                        "READ_ALL_PRODUCT","READ_ONE_PRODUCT","ADD_PRODUCT","EDIT_PRODUCT","DELETE_PRODUCT","READ_BY_FILTR")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("m123")).authorities(
                        "ADD_PRODUCT","DELETE_PRODUCT")
                .and()
                .withUser("operator").password(passwordEncoder().encode("o123")).authorities(
                        "READ_PRODUCT","READ_ONE_PRODUCT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
