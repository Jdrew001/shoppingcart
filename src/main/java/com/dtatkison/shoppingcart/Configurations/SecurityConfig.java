package com.dtatkison.shoppingcart.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails superAdmin =
            User.withUsername("dtatkison@gmail.com").password("{noop}password").roles("ADMIN").build();

        UserDetails admin =
                User.withUsername("djatkison@gmail.com").password("{noop}password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(superAdmin, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/dashboard/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").failureForwardUrl("/loginfailure").defaultSuccessUrl("/dashboard")
                .and().logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
    }
}
