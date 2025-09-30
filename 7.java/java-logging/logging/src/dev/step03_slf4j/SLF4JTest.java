package dev.step03_slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 이제는 Logger 객체를 생성할 때, 특정 구현체의 타입으로 생성하지 않음. 
 * 
 * Log4JTest에서는 Logger 객체의 타입이 다음과 같음
 * import org.apache.logging.log4j.Logger;
 * 
 * Logback의 경우에는 Logger 객체의 타입이 다음과 같음
 * import ch.qos.logback.classic.Logger;
 * 
 * 이제는 SLF4j를 통해 한 단계 추상화된 계층이 org.slf4j.Logger라는 타입으로 활용
 */

public class SLF4JTest {
	private static Logger log = LoggerFactory.getLogger("SLF4JTest");
	// 이 코드는 안바뀜. 로그의 구현체만 바꾸면 됨. 
	
	public static void main(String[] args) {
		log.info("INFO 메세지");
	}

}
