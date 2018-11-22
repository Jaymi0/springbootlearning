package com.jm.oauth2;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import com.jm.oauth2.utils.ClientResources;

@SpringBootApplication
@EnableOAuth2Client
public class SpringbootOauth2SignOnWithGithubApplication extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll() // 主页和登录终端排除授权
		.anyRequest().authenticated() // 其他所有终端都需要已认证的用户
		.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")) // 为认证的用户被重定向到主页
		.and().logout().logoutSuccessUrl("/").permitAll()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	
	private Filter ssoFilter() {
		CompositeFilter compositeFilter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(github(), "/login/github"));
		// 在这里增加外部供应商 
//		filters.add(ssoFilter(facebook(), "/login/facebook"));
		compositeFilter.setFilters(filters);
		return compositeFilter;
	}
	
	/** 
	* @Title: ssoFilter 
	* @Description: TODO 
	* @return Filter
	* @author 502774066
	* @date Nov 22, 20182:19:35 PM
	*/ 
	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices services = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
		services.setRestTemplate(template);
		filter.setTokenServices(services);
		return filter;
	}
	
	/** 
	* @Title: github 
	* @Description: TODO 
	* @return ClientResources
	* @author 502774066
	* @date Nov 22, 20182:24:27 PM
	*/ 
	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
		return new ClientResources();
	}

	/** 
	* @Title: github 
	* @Description: TODO 
	* @return ClientResources
	* @author 502774066
	* @date Nov 22, 20182:24:27 PM
	*/ 
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		return new ClientResources();
	}

	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2SignOnWithGithubApplication.class, args);
	}
	
}
