package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  // 해당 파일로 시큐리티를 활성화
@Configuration  // IOC에 등록해서 메모리에 올라갈 수 있도록 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);  // 기존 시큐리티가 가지고 있는 기능 비활성화
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/", "/user/**","/image/**","subscribe/**","/comment/**").authenticated()
				.anyRequest().permitAll()  
				.and()
			.formLogin()
				.loginPage("/auth/signin")
			.defaultSuccessUrl("/");
	}
}
