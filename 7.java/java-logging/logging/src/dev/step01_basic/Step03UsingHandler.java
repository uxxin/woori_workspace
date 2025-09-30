package dev.step01_basic;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//Handler 객체에 대해 살펴보고, 로그 기록이 파일로 남을 수 있도록 적용
public class Step03UsingHandler {
	private static final Logger logger 
	 = Logger.getLogger("Step03UsingHandler");
	
	public static void main(String[] args) {
		
		try {
			String filePath = "C:\\woori_workspace\\7.java\\step03.txt";
			FileHandler fileHandler = new FileHandler(filePath, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.log(Level.INFO, "INFO 레벨의 로그 메세지입니다.");
			
		}catch(IOException e) {
			
		}
		
		
	}
}
