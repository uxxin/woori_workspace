package dev.step01_basic;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Step01Logging {
	private static final Logger logger = Logger.getLogger("Step01Logging");
	
	public static void main(String[] args) {
		//로그를 남기기 위해서는 Logger 객체를 통해 가능
		//java.util.logging의 기본레벨은 INFO
		//왜 기본 로그레벨이 INFO인지 내부 코드를 들어가서 찾아보기
		logger.log(Level.INFO, "INFO 레벨의 로그 메세지입니다.");
		
		logger.log(Level.WARNING, "WARNING 레벨의 로그 메세지입니다.");
		
		//INFO 레벨의 범위에 속하지 않기 때문에 출력되지 않음. 
		logger.log(Level.FINE, "FINE 레벨의 로그 메세지입니다.");

	}
	
}
