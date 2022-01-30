package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//빈등록 :스프링 컨테이너에서 객체를 관리할 수 있게 하는것
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration //빈등록 (IOC 관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private PrincipalDetailService  principalDetailService;
	
	@Bean //loC 가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
    /**
    *
    * Spring Secureity 에서 인증은 AuthenticationManager 를 통해 이루어지며
    * AuthenticationManagerBuilder 가 AuthenticationManager 를 생성합니다.
    * userDetailsService 를 구현하고 있는 객체로 PrincipalDetailService를 지정해 주며,
    * 비밀번호 암호화를 위해 passwordEncoder 를 지정해줍니다.    
	//시큐리티가 대신 로그인해주는데 password 를 가로채기를 하는데
	//해당 password 가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	 * */	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
			//.csrf().disable()  //csrf 토큰 비활성화(테스트시 걸어두는 것이 좋다)
			.authorizeRequests() //시큐리티 처리에 HttpServletRequest 를 이용한다는 것을 의미			
			.mvcMatchers("/", "/auth/**", "/js/**", "/css/**", "/images/**")
			.permitAll()
			.anyRequest().authenticated();  //위설정한 경로를 제외한 나머지 경로들은 모든 인증을 요구하도록 설정		
			
	  http	 
	  	  .formLogin()	  
          .loginPage("/auth/loginForm")  //로그인 페이지 URL 을 설정         
          .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는  로그인을 가로채서 대신 로그인 해준다. 적지 않으면 .loginPage("/auth/loginForm") 값이 기본값 처리          
          .usernameParameter("username") //로그인시 사용할 파라미터 이름으로 username (또는 email) 여기서는 username 을 지정
          .passwordParameter("password")  //없을시 기본값 :password
          .defaultSuccessUrl("/")    // 로그인 성공시 이동할 URL 을 설정
          .failureUrl("/auth/loginForm/error")  // 로그인 실패시 이동할 URL 을 설정
          .and()
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))  // 임의로그아웃 URL 을 설정하면당 스프링시큐리티가 해당 url 로 로그아웃 처리 . 기본 값은 logout
          .logoutSuccessUrl("/"); //로그아웃 성공시 이동할 URL 을 설정		
	}
	
	

    /**
     *static 디렉터리의 하위 파일은 인증을 무시하도록 설정
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
	
}
