package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;

	private User user;
	private Map<String, Object> attributes;
	
	
	//일반 로그인
	public PrincipalDetails(User user) {
		this.user=user;
	}
	
	// OAuth 로그인
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	
	
	@Override
	public String getName() {
		return user.getUsername();
	}

	
	// 권한 (원래 권한이 여러개 있을 수 있으므로 Collection 루프 돌려야 함)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		collect.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return memberDto.getMemberRole();	// "ROLE_" + @가 되어야 함
//			}
//		});
		//스프링부트 2.5 이상 ROLE 슬러시를 붙이면 안된다. 자동으로 붙여준다. 
		
		authorities.add( () -> { return user.getRole().toString();});
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return user.getPassword();
	}

	// 계정 만료(false) 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 정지(false) 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	// 계정 신용(false) 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	

	// 계정 활성(false) 여부 (예 : 최종 로그인 후 일정기간 경과 여부 -> 휴면계정)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	

}
