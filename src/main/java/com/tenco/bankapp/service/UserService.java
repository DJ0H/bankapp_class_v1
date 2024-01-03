package com.tenco.bankapp.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public int signUp(SignUpFormDto dto) {
		
		String rawPwd = dto.getPassword();
		String hashPwd = passwordEncoder.encode(rawPwd);
		System.out.println("hashPwd: " + hashPwd);
		
		User user = User.builder().username(dto.getUsername())
				.password(hashPwd)
				.fullname(dto.getFullname())
				.originFileName(dto.getOriginFileName())
				.uploadFileName(dto.getUploadFileName())
				.build();
		int resultRowCount = userRepository.insert(user);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("회원 가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resultRowCount;	
	}
	public User signIn(SignInFormDto dto) {
		
		User user = userRepository.findByUsername(dto.getUsername());
		if(user == null) {
			throw new CustomRestfullException("존재하지 않는 계정입니다", HttpStatus.BAD_REQUEST);
		}
		
		boolean isPwdMatched = passwordEncoder.matches(dto.getPassword(), user.getPassword());
	
		if(isPwdMatched == false) {
			throw new CustomRestfullException("비밀번호가 잘못 되었습니다", HttpStatus.BAD_REQUEST);
		}

		return user;
	}
	public User searchUsername(String username) {
		
		
		return userRepository.findByUsername(username);
	}
}
