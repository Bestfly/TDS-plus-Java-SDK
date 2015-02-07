package com.higds.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	public static void info(String msg){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		System.out.println("---------------------------------------");
		System.out.println(dateFormat.format(new Date())+" "+msg);
	}
}
