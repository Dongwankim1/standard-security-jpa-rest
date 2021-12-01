package com.sraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StandardSecurityJpaRestApplication {
	
	//해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
		@Bean
		public BCryptPasswordEncoder encodePwd() {
			return new BCryptPasswordEncoder();
		}
		
	
	public static void main(String[] args) {
		SpringApplication.run(StandardSecurityJpaRestApplication.class, args);
	}

}
