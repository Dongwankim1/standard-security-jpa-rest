package com.sraw.security.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sraw.security.model.User;
import com.sraw.security.repository.UserRepository;


//시큐리티 설정에서 loginProcessingUrl("login");
// /lgoin 요청이 오면 자동으로 userDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	//시큐리티 session = Authentication = UserDetails
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	User userEntity = userRepository.findByUsername(username);
	if(userEntity!=null) {
		return new PrincipalDetails(userEntity);
	}
		
		return null;
	}

}
