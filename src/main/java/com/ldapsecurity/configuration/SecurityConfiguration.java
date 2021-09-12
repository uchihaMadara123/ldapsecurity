package com.ldapsecurity.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import com.ldapsecurity.securityexceptionhandler.CustomAuthenticationExcpetionHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${ldap.url}")
	String corsUrl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
				.url(this.corsUrl).and().passwordCompare().passwordEncoder(new BCryptPasswordEncoder())
				.passwordAttribute("userPassword");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure http is called");
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
				.permitAll().failureHandler(getCustomAuthenticationExcpetionHandler()).and().logout()
				.invalidateHttpSession(true);
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return new DefaultSpringSecurityContextSource(Collections.singletonList("ldap://localhost:8389"),
				"dc=springframework,dc=com");
	}

	@Bean
	public CustomAuthenticationExcpetionHandler getCustomAuthenticationExcpetionHandler() {
		return new CustomAuthenticationExcpetionHandler();
	}

}
