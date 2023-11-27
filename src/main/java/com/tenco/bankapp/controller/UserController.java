package com.tenco.bankapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.bankapp.dto.SignInFormDto;
import com.tenco.bankapp.dto.SignUpFormDto;
import com.tenco.bankapp.handler.exception.CustomRestfullException;
import com.tenco.bankapp.repository.entity.User;
import com.tenco.bankapp.service.UserService;
import com.tenco.bankapp.utils.Define;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession httpSession;
//	public UserController(UserService userService) {
//		this.userService = userService;
//	}
	// 회원 가입 페이지 요청
	@GetMapping("sign-up")
	public String signUp() {
		return "user/signUp";
	}
	@GetMapping("sign-in")
	public String signIn() {
		return "user/signIn";
	}
	
	@PostMapping("sign-up")
	public String signUpProc(SignUpFormDto dto) {
		// 1. 유효성 검사
		if(dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfullException("username을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfullException("Password을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if(dto.getFullname() == null || dto.getFullname().isEmpty()) {
			throw new CustomRestfullException("Fullname을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		int resultRowCount = userService.signUp(dto);
		if(resultRowCount != 1) {
			
		}
		return "redirect:/user/sign-in";
	}
	@PostMapping("sign-in")
	public String signInProc(SignInFormDto dto) {
		if(dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfullException("username을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfullException("Password을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		User principal = userService.signIn(dto);
		httpSession.setAttribute(Define.PRINCIPAL, principal);
		
		return "redirect:/account/list";
	}
	@GetMapping("/logout")
	public String logOut() {
		httpSession.invalidate();
		return "redirect:/user/sign-in";
	}
}
