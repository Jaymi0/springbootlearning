/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.oauth2.config 
 * @author: 502774066   
 * @date: Nov 21, 2018 10:30:22 AM 
 */

package com.jm.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/** 
 * @ClassName: ResourceServerConfig 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 21, 2018 10:30:22 AM  
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("users-info");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .requestMatchers()
        .antMatchers("/users/**")
        .and().authorizeRequests()
        .antMatchers("/users/**")
        .authenticated();
	}
	
}
