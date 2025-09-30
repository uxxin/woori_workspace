package dev.syntax.step05_mutex;

/**
 * Java에서 제공하는 synchronized를 직접 구현해보는 예시
 */

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//SharedData sharedData = new SharedData();
		SharedData sharedData = new SharedData(new Mutex());
		
		
		// 스레드 2개 생성
		// 각 스레드에게 sum() 작업을 수행하도록 지정
		Thread t1 = new Thread(sharedData::sum);
		Thread t2 = new Thread(sharedData::sum);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("최종 합계: "+ sharedData.getSum());
		
	}
}
