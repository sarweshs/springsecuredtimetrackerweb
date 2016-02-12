package com.ibm.timetrackerweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {			 
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());;
		
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.csrf().disable().authorizeRequests()
		.antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
		.regexMatchers(HttpMethod.POST,"/timetracker/save.*").access("hasRole('ROLE_ADMIN')")
		.regexMatchers(HttpMethod.GET,"/timetracker/find*.*").access("hasRole('ROLE_ADMIN')")
		//.regexMatchers(HttpMethod.POST,"/timetracker/save.*").authenticated()
		//.regexMatchers(HttpMethod.GET,"/timetracker/find*.*").authenticated()
		.anyRequest().permitAll()
		.and()
		  .formLogin().loginPage("/login")
		  .usernameParameter("username").passwordParameter("password").and()
		  .httpBasic()
		.and()
		  .logout().logoutSuccessUrl("/login?logout")	
		 .and()
		 .exceptionHandling().accessDeniedPage("/403")
		
		  ;
	}
	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
}
