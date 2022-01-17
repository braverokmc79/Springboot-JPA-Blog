package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//빈등록 :스프링 컨테이너에서 객체를 관리할 수 있게 하는것

@Configuration //빈등록 (IOC 관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests() //시큐리티 처리에 HttpServletRequest 를 이용한다는 것을 의미
			.antMatchers("/auth/**")
			.permitAll()
			.anyRequest().authenticated()  //위설정한 경로를 제외한 나머지 경로들은 모든 인증을 요구하도록 설정		
		.and()
			.formLogin()
			.loginPage("/auth/loginForm");
		
		
				
	}
	
	
	
	
}
