package com.shaposhnikandrii.urlshortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/{hash:[0-9a-zA-Z]{1,6}}").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/static/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/admin-panel/login").permitAll()
        .defaultSuccessUrl("/admin-panel/records", true)
        .passwordParameter("password")
        .usernameParameter("username")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .logoutSuccessUrl("/admin-panel/login");
  }

}