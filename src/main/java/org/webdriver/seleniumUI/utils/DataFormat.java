package org.webdriver.seleniumUI.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormat {

	public   static String formatDate(Date date,String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.println(formatter.format(date).toString());
		return formatter.format(date).toString();

	}
	public  static String formatDate(long date,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

	public static void main(String[] args) {
		//
	}

}
