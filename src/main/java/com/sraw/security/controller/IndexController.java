package com.sraw.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sraw.security.config.auth.PrincipalDetails;
import com.sraw.security.model.User;
import com.sraw.security.service.UserService;
	
	

@Controller
public class IndexController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/test/login")
	public @ResponseBody String loginTest(Authentication authentication,@AuthenticationPrincipal PrincipalDetails userDetails) { //DI(의존성 주입)
		System.out.println("/test/login =============");
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("authentication : "+principalDetails.getUser());
		
		System.out.println("userDetails : "+userDetails.getUser());
		return "세션정보확인하기";
		
	}
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String loginOAuthTest(Authentication authentication,@AuthenticationPrincipal OAuth2User oauth) { //DI(의존성 주입)
		System.out.println("/test/oauth/login =============");
		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("authentication : "+oauth2User.getAttributes());
		System.out.println("oauth2User : "+oauth.getAttributes());
	
		return "OAuth 세션 정보 확인";
		
	}
	
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/join")
	public  String join( User user) throws Exception {
		userservice.createUser(user);
		
		return "redirect:/loginForm";
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터";
	}
	
	
	
	
}
