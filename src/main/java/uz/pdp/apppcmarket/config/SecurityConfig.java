package uz.pdp.apppcmarket.config;

import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("superAdmin").password(passwordEncoder().encode("s123")).roles("SUPER_ADMIN").
                authorities("READ_ALL_PRODUCT","READ_ONE_PRODUCT","READ_BY_FILTR","ADD_PRODUCT","EDIT_PRODUCT","DELETE_PRODUCT","ORDER")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("m123")).roles("MODERATOR").
                authorities("ADD_PRODUCT","EDIT_PRODUCT")
                .and()
                .withUser("operator").password(passwordEncoder().encode("o123")).roles("OPERATOR").authorities("ORDER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/product/**").hasAuthority("ADD_PRODUCT")
                .antMatchers(HttpMethod.PUT, "/api/product/**").hasAnyAuthority("EDIT_PRODUCT")
                .antMatchers( "/api/orders/**").hasAuthority("ORDER")
                .antMatchers("/api/attachment/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/basketProduct/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/basket/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/category/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/character/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/customer/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/property/**").hasRole("SUPER_ADMIN")

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
