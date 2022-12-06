package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController  // data를 return함
@ControllerAdvice  // 모든 exception을 여기서 낚아챔
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		// return e.getErrorMap();  // map은 json으로 넘어감
		// return new CMRespDto(-1, e.getMessage(), e.getErrorMap());  //  springboot 기본 messageConverter 설정에 의해 json으로 넘어감
		return Script.back(e.getErrorMap().toString());
		
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때는 script가 좋음. myOpinion) 자바 코드에 javascript 들어가는거 좋은거 아닌거 같음
		// 2. Ajax 통신 - CMRespDto 사용
		// 3. Android 통신 - CMRespDto 사용
		// 1번은 브라우저가 응답받는거고, 2,3번은 개발자가 받는 것의 차이가 있는거다.
	}
}
