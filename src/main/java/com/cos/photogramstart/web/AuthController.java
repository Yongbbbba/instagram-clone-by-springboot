package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할 때 사용
@Controller  // IoC에 등록, 파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;	
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public /*@ResponseBody*//*이게 붙어있으면 데이터를 return함. view가 아니라*/ String signup(@Valid/*DTO에 대한 유효성 검증 시도*/ SignupDto signupDto, BindingResult bindingResult) { // key=value(x-www-form-urlencoded)
//		log.info(signupDto.toString());
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			//return "error occur";
			throw new CustomValidationException("validation check fail", errorMap);
		}else {
			// User <- SignupDto
			User user = signupDto.toEntity();
			log.info(user.toString());
			
			User userEntity = authService.회원가입(user);
			
			return "auth/signin";  // 얘는 forward 방식임, request 객체를 유지하게 됨
//		return "redirect:/auth/signin";  // 얘는 redirect
		}
		
	}
}
