package dev.syntax.step02_create_thread;

//Thread 객체를 생성할 수 있는 방법 2번째
//Runnable 인터페이스를 구현하여 스레드를 생성하는 방식
public class Ex02 {
	public static void main(String[] args) {
		//1. SecondThread 객체 생성
		SecondThread secondThread = new SecondThread();
		
		//2. 실제 스레드를 실행하기 위해 Thread 타입의 스레드 객체를 생성
		Thread thread = new Thread(secondThread);
		//Thread(Runnable)
		
		//3. 스레드 실행하기 위해서 start()를 호출하는 것은 기존 방식과 동일함
		thread.start();
	}
}
