package dev.step01_basic;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Step02ConfigLevel {
	//로깅을 수행할 수 있는 Logger 객체 생성
	private static final Logger logger 
	 = Logger.getLogger("Step02ConfigLevel");
	
	public static void main(String[] args) {
	 
	 // 부모 로거 객체의 로그 레벨도 변경
	 Logger rootLog = Logger.getLogger("");
	 rootLog.setLevel(Level.FINE);
	 rootLog.getHandlers()[0].setLevel(Level.FINE);
	 
	 logger.log(Level.INFO, "INFO 레벨의 로그 메시지입니다.");
	 
	 logger.log(Level.WARNING, "WARNING 레벨의 로그 메시지입니다.");
	 
	 // INFO 레벨의 범위에 속하지 않기 때문에 출력되지 않음
	 logger.log(Level.FINE, "FINE 레벨의 로그 메시지입니다.");
	}
}
