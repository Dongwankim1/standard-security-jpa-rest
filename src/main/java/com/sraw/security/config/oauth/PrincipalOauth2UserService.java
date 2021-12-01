package com.sraw.security.config.oauth;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.sraw.security.config.auth.PrincipalDetails;
import com.sraw.security.model.User;
import com.sraw.security.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest : "+userRequest.getClientRegistration());
		// 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Cleint라이브러리) -> AccessToken요청
		// userRequest 정보 -> 회원프로필 받아야함(loadUser함수) -> 구글로부터 회원프로필 받아준다.
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getClientId(); //google
		String providerId = oauth2User.getAttribute("sub");
		String username = provider+"_"+providerId;
		String password = BCryptPasswordEncoder.encode("겟인데어");
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			Date date =new Date();
			Timestamp ts = new Timestamp(date.getTime());
			userEntity = new User(username,email,role,password,ts,provider,providerId);
			userRepository.save(userEntity);
		}
		
		
		return new PrincipalDetails(userEntity,oauth2User.getAttributes());
	}
	
}
