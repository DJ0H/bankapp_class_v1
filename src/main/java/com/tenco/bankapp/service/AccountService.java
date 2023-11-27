package com.tenco.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bankapp.dto.DepositFormDto;
import com.tenco.bankapp.dto.SaveFormDto;
import com.tenco.bankapp.dto.WithdrawFormDto;
import com.tenco.bankapp.dto.transferFormDto;
import com.tenco.bankapp.handler.exception.CustomRestfullException;
import com.tenco.bankapp.repository.entity.Account;
import com.tenco.bankapp.repository.entity.History;
import com.tenco.bankapp.repository.interfaces.AccountRepository;
import com.tenco.bankapp.repository.interfaces.HistoryRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Transactional
	public void createAccount(SaveFormDto dto, Integer princicalId) {
		// 계좌 중복 확인
		
		Account account = Account.builder().number(dto.getNumber()).balance(dto.getBalance()).password(dto.getPassword()).userId(princicalId).build();
		int resultRowCount = accountRepository.insert(account);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("게좌 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public List<Account> selectAccount(Integer pincipalId) {
		List<Account> accountList = accountRepository.findByUserId(pincipalId);
		return accountList;
	}
	
	@Transactional
	public void updateAccountWithdraw(WithdrawFormDto dto, Integer id) {
		Account accountEntity = accountRepository.findByNumber(dto.getWAccountNumber());
		if(accountEntity == null) {
			throw new CustomRestfullException("해당 계좌가 없습니다", HttpStatus.BAD_REQUEST);
		}
		if(accountEntity.getUserId() != id) {

			throw new CustomRestfullException("본인 소유 계좌가 아닙니다", HttpStatus.UNAUTHORIZED);
		}
		if(accountEntity.getPassword().equals(dto.getPassword()) == false) {
			
			throw new CustomRestfullException("출금 계좌 비밀번호가 틀렸습니다", HttpStatus.UNAUTHORIZED);
		}
		if(accountEntity.getBalance() < dto.getAmount()) {
			throw new CustomRestfullException("계좌 잔액이 부족합니다", HttpStatus.UNAUTHORIZED);
		}
		accountEntity.withdraw(dto.getAmount());
		accountRepository.updateById(accountEntity);
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWBalance(accountEntity.getBalance());
		history.setDBalance(null);
		history.setWAccountId(accountEntity.getId());
		history.setDAccountId(null);
		
		int resultRowCount = historyRepository.insert(history);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("정상 처리 되지 않았습니다", HttpStatus.BAD_REQUEST);
		}
	}
	// 입금 처리 기능
	@Transactional
	public void updateAccountDeposit(DepositFormDto dto) {
		Account accountEntity = accountRepository.findByNumber(dto.getDAccountNumber());
		if(accountEntity == null) {
			throw new CustomRestfullException("해당 계좌가 없습니다", HttpStatus.BAD_REQUEST);
		}
		accountEntity.deposit(dto.getAmount());
		accountRepository.updateById(accountEntity);
		
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWBalance(null);
		history.setDBalance(accountEntity.getBalance());
		history.setWAccountId(null);
		history.setDAccountId(accountEntity.getId());
		
		int resultRowCount = historyRepository.insert(history);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Transactional
	public void updateAccountTransfer(transferFormDto dto, Integer id) {
		Account dAccountEntity = accountRepository.findByNumber(dto.getDAccountNumber());
		Account wAccountEntity = accountRepository.findByNumber(dto.getWAccountNumber());
		
		
		if(dAccountEntity == null) {
			throw new CustomRestfullException("입금받을 해당 계좌가 없습니다", HttpStatus.BAD_REQUEST);
		}
		if(wAccountEntity == null) {
			throw new CustomRestfullException("출금계좌가 없습니다", HttpStatus.BAD_REQUEST);
		}
		
		wAccountEntity.checkOwner(id);
		wAccountEntity.checkPassword(dto.getPassword());
		wAccountEntity.checkBalance(dto.getAmount());
		
		dAccountEntity.deposit(dto.getAmount());
		accountRepository.updateById(dAccountEntity);
		wAccountEntity.withdraw(dto.getAmount());
		accountRepository.updateById(wAccountEntity);
		
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWBalance(wAccountEntity.getBalance());
		history.setDBalance(dAccountEntity.getBalance());
		history.setWAccountId(wAccountEntity.getId());
		history.setDAccountId(dAccountEntity.getId());
		int resultRowCount = historyRepository.insert(history);
		if(resultRowCount != 1) {
			throw new CustomRestfullException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
