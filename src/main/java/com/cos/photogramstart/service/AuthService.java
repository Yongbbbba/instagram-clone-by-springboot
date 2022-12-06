package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC, 트랜잭션 관리를 해줌
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;  // bean 객체로 등록해놓음

	@Transactional  // write(insert, delete, update)할 때 사용, DB 작업 시 트랜잭션으로 이를 원자적으로 처리하도록 함
	public User 회원가입(User user) {
		// 회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");  // 관리자는 ROLL_ADMIN
		
		User userEntity = userRepository.save(user);  // User type return 
		return userEntity;
	}
}
