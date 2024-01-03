package com.tenco.bankapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.bankapp.dto.SaveFormDto;
import com.tenco.bankapp.handler.exception.CustomPageException;
import com.tenco.bankapp.handler.exception.CustomRestfullException;
import com.tenco.bankapp.handler.exception.UnAuthorizedException;
import com.tenco.bankapp.repository.entity.Account;
import com.tenco.bankapp.repository.entity.History;
import com.tenco.bankapp.repository.entity.User;
import com.tenco.bankapp.service.AccountService;
import com.tenco.bankapp.utils.Define;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AccountService accountService;
	
	//임시 예외 발생 확인
	
	@GetMapping({"/list","/"})
	public String list(Model model) {
		User principal = (User)session.getAttribute(Define.PRINCIPAL);
		
		// 인증검사
		
		List<Account> accountList = accountService.selectAccount(principal.getId());
		if(accountList.isEmpty()) {
			model.addAttribute("accountList",null);
		}
		else {
			model.addAttribute("accountList",accountList);
		}
		
		return "account/list";
	}
	@GetMapping("/save")
	public String save() {
	
		return "account/save";
	}
	
	@PostMapping("/save")
	public String saveProc(SaveFormDto dto) {
		User principal = (User)session.getAttribute(Define.PRINCIPAL);
		
		if(dto.getNumber() == null || dto.getNumber().isEmpty()) {
			throw new CustomRestfullException("계좌번호를 입력하시오.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfullException("계좌비밀번호를 입력하시오.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getBalance() == null || dto.getBalance() <= 0) {
			throw new CustomRestfullException("잘못된 입력입니다.", HttpStatus.BAD_REQUEST);
		}
		accountService.createAccount(dto, principal.getId());
		return "account/list";
	}
	
	// 계좌 상세보기 화면 요청 처리
	// 데이터를 입력 받는 방법 정리
	// http://localhost/account/detail/1
	// http://localhost/account/detail/1?type=deposit
	// http://localhost/account/detail/1?type=withdraw
	// 기본 값 세팅 가능
	@GetMapping("/detail/{accountId}")
	public String detail(@PathVariable Integer accountId, 
			@RequestParam(name = "type", defaultValue = "all", required = false) String type,
			Model model) {
		// 인증 검사, 유효성 검사
		User principal = (User)session.getAttribute(Define.PRINCIPAL);

		
		// 상세 보기 화면 요청시 --> 데이터를 내려주어야한다
		// account 데이터, 접근주체, 거래 내역 정보
		Account account = accountService.findById(accountId);
		List<History> historyList = accountService.readHistoryListByAccount(type, accountId); 
		model.addAttribute(Define.PRINCIPAL, principal);
		model.addAttribute("account", account);
		model.addAttribute("historyList", historyList);
		
		return "account/detail";
	}
}
