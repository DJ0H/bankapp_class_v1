package com.tenco.bankapp.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeStampUtil {
	// 상태 값을 가기는 변수를 가지면 안된다
	public static String timestampToString(Timestamp time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(time);
	}
}
