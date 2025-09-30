package dev.syntax.step05_mutex;

//상호 배제 기법(Mutex)을 synchronized를 활용하여 구현한 코드
public class Mutex {
	
	//특정 스레드가 lock을 획득했는지 여부를 확인하기 위한 flag 변수
	private boolean lock = false;
	
		// wait(), notify()를 활용해야 함
		// wait() -> 스레드를 대기 상태로 전환
		// notify() -> 대기 중인 스레드를 깨우는 역할
	
	// TODO: 특정 스레드가 lock을 획득하는 메서드
	public synchronized void acquired() {
		
		// 가장 처음으로 들어온 스레드는 lock이 false이기 때문에 while을 지나침
		while(lock) { 
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 가장 처음으로 들어온 스레드는 lock을 획득했기 때문에 lock을 true로 변경
		this.lock = true;
	}
	
	// TODO: 특정 스레드가 획득했던 lock을 해제(반납)하는 메서드
	public synchronized void release() {
		//lock을 해제하는 로직
		this.lock = false; // lock 해제
		this.notify(); // t1 스레드가 t2 스레드에게 알림 (t2 스레드를 RUNNABLE 상태로 전환됨.)
	}
}
