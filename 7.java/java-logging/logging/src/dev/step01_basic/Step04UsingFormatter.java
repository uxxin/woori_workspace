package dev.step01_basic;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import java.text.SimpleDateFormat;
import java.util.Date;


//- Formatter 객체에 대해 살펴보고, CustomFormatter 객체를 생성하여 
//로그 메시지를 적절하게 포매팅 후 적용하기
public class Step04UsingFormatter extends Formatter{
	
	@Override
	public String format(LogRecord record) {
		StringBuffer str = new StringBuffer(1000);
		
		//TODO: 포맷팅 코드 작성하기
	        return str.toString();
	}
	

}
