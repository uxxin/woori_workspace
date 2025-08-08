package dev.syntax.step05_mutex;

/**
 *  서로 다른 스레드들이 공유하는 클래스
 */

public class SharedData {
	
	private int sharedValue = 0;
	
	private Mutex mutex;
	
	public SharedData(Mutex mutex) {
		super();
		this.mutex = mutex;
	}

	//sharedValue를 1부터 100만까지 합산하는 메서드 
	public void sum() {
		
		//TODO: 동기화 처리 로직
		mutex.acquired();
		for(int i = 0; i<100; i++) {
			sharedValue++;			
		}
		//TODO: 동기화 처리 로직
		mutex.release();
	}
	
	public int getSum() {
		return sharedValue;
	}
}
