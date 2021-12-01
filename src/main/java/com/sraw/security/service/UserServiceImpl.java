package com.sraw.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sraw.security.model.User;
import com.sraw.security.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void createUser(User user) throws Exception{
		
		
		
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		userRepository.save(user);
	
		
		// TODO Auto-generated method stub
		
	}
}
