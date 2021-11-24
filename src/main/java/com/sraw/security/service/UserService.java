package com.sraw.security.service;

import org.springframework.stereotype.Service;

import com.sraw.security.model.User;


public interface UserService {
	public void createUser(User user) throws Exception;
}
