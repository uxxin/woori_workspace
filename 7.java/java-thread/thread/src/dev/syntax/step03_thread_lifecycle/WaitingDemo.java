package dev.syntax.step03_thread_lifecycle;

public class WaitingDemo implements Runnable {
	
	@Override
	public void run() {
		//t2 스레드 용도의 작업 작성
		System.out.println("t2 스레드 작업 수행 시작");
		
		try {
			Thread.sleep(1000);//1초 지연
			
		} catch (Exception e) {
		}
		//t2 스레드가 실행 중일 때, t1 스레드의 상태를 확인
		//t1 스레드는 Ex04Waiting 클래스로 생성
		System.out.println("t1의 현재 상태: "+Ex04Waiting.t1.getState());
		System.out.println("t2 스레드 작업 수행 종료");
	}
}
