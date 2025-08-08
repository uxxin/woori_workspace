package dev.syntax.step03_thread_lifecycle;

//1. Blocked 상태를 확인하기 위한 예시(Ex03Blocked.java)
public class BlockedDemo implements Runnable {

	@Override
	public void run() {
		// 2. 스레드가 수행할 작업 내용 작성 부분
		criticalArea();
	}
	
	//임계 영역 생성 - 특정 시점에는 오직 하나의 스레드만 접근할 수 있는 영역(메서드 내부)
	//예를 들어 스레드 A가 criticalArea()를 호출했을 경우, 스레드 B는 해당 메서드를 호출할 수 없고, 
	//메서드 앞에서 대기
	
	//스레드 B가 메서드 앞에서 대기 중
	public static synchronized void criticalArea() {
		while(true) {} // 스레드 A가 사용 중..
		
	}
}
