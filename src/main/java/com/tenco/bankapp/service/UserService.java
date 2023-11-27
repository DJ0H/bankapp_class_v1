package com.tenco.bankapp.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bankapp.dto.SignInFormDto;
import com.tenco.bankapp.dto.SignUpFormDto;
import com.tenco.bankapp.handler.exception.CustomRestfullException;
import com.tenco.bankapp.repository.entity.User;
import com.tenco.bankapp.repository.interfaces.UserRepository;

@Service
@Mapper
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int signUp(SignUpFormDto dto) {
		User user = User.builder().username(dto.getUsername()).password(dto.getPassword()).fullname(dto.getFullname()).build();
		int resultRowCount = userRepository.insert(user);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("회원 가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resultRowCount;	
	}
	public User signIn(SignInFormDto dto) {
		User user = userRepository.findByUsernameAndPassword(dto);
		if(user == null) {
			throw new CustomRestfullException("아이디 혹은 비번이 틀렸습니다", HttpStatus.BAD_REQUEST);
		}
		return user;
	}
}
