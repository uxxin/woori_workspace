package dev.syntax.step03_thread_lifecycle;

/**
 * 스레드 생명주기 - WAITING
 * 
 * 어떤 스레드(t1)가 실행 중인 상태에서 다른 스레드(t2)가 특정 작업에 대한 수행을 먼저 마칠 때까지 기다리는 상태
 * 이때 t2의 작업이 마칠 때 까지 기다리면서 t1은 WAITING 상태
 */

public class Ex04Waiting implements Runnable {
	
	public static Thread t1;
	
	public static void main(String[] args) {
		// t1이 작업 먼저 시작하다가, t2가 작업을 중간에 시작하고, t1은 t2가 끝날 때 까지 대기하다가
		// t2가 작업을 끝내면 t1이 남은 작업을 마저 수행하고 종료하는 시나리오
		
		// t1 스레드 생성
		t1 = new Thread(new Ex04Waiting());
		
		// t1 스레드 시작
		t1.start();
		
	}
	
	@Override
	public void run() {
		// t1 스레드(EX04Waiting)가 수행할 작업 작성
		System.out.println("t1 스레드 작업 수행 시작");
		
		//t1 스레드 내에서 다른 스레드인 t2 생성
		Thread t2 = new Thread(new WaitingDemo());
		
		//t2 스레드 시작
		t2.start();
		
		//t2 스레드의 작업이 끝날 때 까지 대기한 후 (WAITING), t1 스레드가 마저 실행되도록
		try {
			System.out.println("t2의 작업이 마칠 때까지 WAIITING 상태로 전환");
			t2.join();  //t2가 끝난 후 마저 수행하겠다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		System.out.println("t1 스레드가 남은 작업을 수행 후 종료");
	}
}
