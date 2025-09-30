package dev.syntax.step03_thread_lifecycle;

/**
 * 2개의 스레드를 각각 생성(스레드 1, 스레드 2)
 * 
 * 스레드 1은 3초 동안 동작 후 종료 - sleep(초) 활용
 * 스레드 2는 2초 동안 동작 후 종료 - sleep(초) 활용
 * 
 * 메인 스레드는 스레드 1, 2가 모두 작업을 종료한 후에 종료해야 함. 
 */

public class Ex06JoinWithMultiThread {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
		      try {
		        System.out.println("스레드1이 3초 동안 동작");
		        Thread.sleep(3000);
		        System.out.println("스레드1 동작 완료");
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    });
		
		Thread t2 = new Thread(() -> {
		      try {
		        System.out.println("스레드2이 2초 동안 동작");
		        Thread.sleep(2000);
		        System.out.println("스레드2 동작 완료");
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    });
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread1, thread2의 작업 이후 메인 스레드 진행");
	}
	

}
