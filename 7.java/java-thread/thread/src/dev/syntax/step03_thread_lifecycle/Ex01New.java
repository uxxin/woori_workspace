package dev.syntax.step03_thread_lifecycle;

/**
 * 스레드 객체 생명주기 - NEW
 * 스레드 객체가 생성되었지만, 아직 실행은 되지 않은 상태
 */

public class Ex01New {
	public static void main(String[] args) {
		//스레드 객체 생성
		Thread thread = new Thread(()->{
			System.out.println("생성된 스레드 ID: "+Thread.currentThread().getId());
		});
		
		//스레드 객체의 상태 확인 - thread.getState()
		System.out.println(thread.getState()); //thread.start()를 호출하지 않았기 때문에 NEW 상태
		
	}
}
