package dev.step02_exlib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.*;


//Log4j 라이브러리 적용 테스트

/**
 * java.util.logging 패키지가 아닌,
 * Apache 재단에서 제공하는 log4j 라이브러리 설치(log4j.jar)
 */
public class Log4JTest {
	private static Logger logger = LogManager.getLogger(Log4JTest.class);
	public static void main(String[] args) {
		logger.info("INFO 레벨 메세지");
		
		logger.debug("DEBUG 레벨 메세지");
		//TODO: 에러 메서지를 확인 후 직접 에러 해결, log4j를 통한 로그 메세지가 잘 적용되도록
		//잘 적용되었을 경우, 새로 받은 라이브러리(core)의 클래스도 확인해보기
		try {
			System.out.println(1/0);			
		} catch(Exception e) {
			logger.error("Error", e);
		}
		
	}
}
