package com.sraw.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sraw.security.model.User;
//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 IoC된다. 이유는 JpaRepository를 상속 했기에
public interface UserRepository extends JpaRepository<User, Integer> {

}
