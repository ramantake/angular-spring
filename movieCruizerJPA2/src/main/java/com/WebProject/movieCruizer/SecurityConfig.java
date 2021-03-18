package com.WebProject.movieCruizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.WebProject.movieCruizer.model.JWTAuthFilter;
import com.WebProject.movieCruizer.service.AppUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
AppUserDetailsService appUserDetailsService;

@Override
protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable().
 authorizeRequests()
.antMatchers("/customer-ang")
.hasRole("user")
.antMatchers("/favorite-ang")
.hasRole("user")
.antMatchers("/add-ang/*")
.hasRole("user")
.antMatchers("/delete-ang/*")
.hasRole("user");
http.formLogin();
http.addFilter(new JWTAuthFilter(authenticationManager()));
}

/*@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //super.configure(auth);
  auth.inMemoryAuthentication()
      .withUser("user")
    .password(("{noop}userpwd"))
    .roles("user")
    .and()
    .withUser("admin")
    .password(("{noop}adminpwd"))
    .roles("admin");
}
*/
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(appUserDetailsService)
.passwordEncoder(passwordEncoder());
}

private PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

}
