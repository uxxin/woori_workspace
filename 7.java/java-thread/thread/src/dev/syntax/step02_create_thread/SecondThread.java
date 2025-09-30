package dev.syntax.step02_create_thread;

public class SecondThread implements Runnable{

	@Override
	public void run() {
		System.out.println("스레드 작업 시작");
		System.out.println("생성된 스레드 ID: "+Thread.currentThread().getId());
		
	}
}
