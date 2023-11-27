package com.tenco.bankapp.dto;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class HistoryResponseDto {
	private Integer id;
	private Long amount;
	private Long wBalance;
	private Long dBalance;
	private Integer wAccountId;
	private Integer dAccountId;
	private Timestamp createdAt;
	private Integer user_id;
}
