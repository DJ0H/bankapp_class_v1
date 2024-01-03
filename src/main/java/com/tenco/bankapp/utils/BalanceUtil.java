package com.tenco.bankapp.utils;

import java.text.DecimalFormat;

public class BalanceUtil {
	public static String formatBalance(Long balance) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		return decimalFormat.format(balance) + "원";
	}
}
