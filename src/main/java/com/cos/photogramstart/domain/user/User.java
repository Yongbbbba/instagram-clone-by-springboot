package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적(DB)으로 저장할 수 있는  API를 제공)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity  // DB 테이블에 맵핑
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 번호 증가 전략을 데이터베이스의 테이블 설정에 따라감
	private int id;
	
	@Column(length = 20, unique = true)
	private String username;
	
	@Column(nullable = false) 
	private String password;
	
	@Column(nullable = false) 
	private String email;
	
	private String name;
	
	private String bio;  // 자기소개
	
	private String website;  // 웹사이트
	
	private String phone;
	
	private String gender;
	
	private String profileImageUrl;  // 프로필사진
	
	private String role;  // 권한
	
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 insert 되기 직전에 실행이 됨 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
