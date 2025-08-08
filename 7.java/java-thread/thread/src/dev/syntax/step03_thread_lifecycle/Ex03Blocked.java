package dev.syntax.step03_thread_lifecycle;

/**
 * 스레드 생명주기 - Blocked(차단됨)
 * 
 * 어떤 스레드가 현재 작업을 수행할 자격이 없을 때의 상태를 의미
 * 작업을 수행 중인 다른 스레드에 의해서 모니터 락(Monition lock)이 걸려서
 * 자신이 수행 중이던 작업이 대기 상태로 전환되고, 
 * 
 * 다른 스레드에 의해 임계 영역(Critical Section)에 접근하려고 할 때, 
 * Lock을 획득하기 위해서 대기 중인 상태(Blocked)
 * 
 */
public class Ex03Blocked {
	public static void main(String[] args) throws InterruptedException {
		
		// 1. 2개의 스레드 객체 생성
		Thread t1 = new Thread(new BlockedDemo());
		Thread t2 = new Thread(new BlockedDemo());
		
		// 2. 각각의 스레드 실행
		t1.start();
		t2.start();
		
		Thread.sleep(1000);
	
		System.out.println("t2: " + t2.getState());
		
	}
}
