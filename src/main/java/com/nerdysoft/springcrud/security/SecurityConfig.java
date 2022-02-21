package com.nerdysoft.springcrud.security;

import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authentication Manager Configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username ->  {
            User user = userRepository.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("user: " + username + " not found");
            }
            return new AppUserDetails(user);
        });
    }

    /**
     * Web Security Configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic()
                .and().sessionManagement().disable();
        http.authorizeRequests()
                .antMatchers("/users")
                        .permitAll()
                .antMatchers("/items", "/items/**", "/orders", "/orders", "/users/**")
                        .authenticated()
                .antMatchers("/orders/details/")
                        .hasAuthority("ADMIN");
    }

    /**
     * Setting encryption preference
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



















