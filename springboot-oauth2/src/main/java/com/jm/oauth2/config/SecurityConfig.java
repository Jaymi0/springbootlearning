/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.oauth2.config 
 * @author: 502774066   
 * @date: Nov 21, 2018 10:18:28 AM 
 */

package com.jm.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** 
 * @ClassName: SecurityConfig 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 21, 2018 10:18:28 AM  
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("123456").authorities("ROLE_USER");
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.and()
			.logout().permitAll();
	}

}
