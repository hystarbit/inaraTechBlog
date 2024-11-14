package com.blog.inara;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.inara.member.MemberService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	public SecurityConfig(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/register", "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), userService));
	}
}
