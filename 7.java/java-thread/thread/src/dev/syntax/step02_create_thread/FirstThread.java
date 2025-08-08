package dev.syntax.step02_create_thread;

// 1. Thread 클래스를 확장(extends)하여 커스텀 스레드 구현
// -> 다중 상속 불가
public class FirstThread extends Thread{

	@Override
	public void run() {
		//2.run() 메서드를 오버라이딩하여 해당 스레드가 수행할 작업 내용을 작성
		System.out.println("스레드 작업 시작");
		System.out.println("생성된 스래드 ID: "+Thread.currentThread().getId());
	}
	

}
