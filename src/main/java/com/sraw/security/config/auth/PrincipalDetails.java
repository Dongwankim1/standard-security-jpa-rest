package com.sraw.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sraw.security.model.User;

import lombok.Data;



//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (Security ContextHolder)
//오브젝트 => Authentication 타입 객체
//Authentication 안에 User정보가 있어야 됨.
//User 오브젝트타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails


@Data
public class PrincipalDetails implements UserDetails,OAuth2User{
	
	private User user;
	private Map<String,Object> attributes;
	
	//일반 로그인
	public PrincipalDetails(User user) {
		this.user =user;
	}
	
	
	// Oauth 로그인
	public PrincipalDetails(User user,Map<String,Object> attributes) {
		this.user = user;
		this.attributes= attributes;
		// TODO Auto-generated constructor stub
	}
	
	
	public User getUser() {
		return user;
	}





	//해당 User의 권한을 리턴하는 곳!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("sub");
	}
	
	
	
}
