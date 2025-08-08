package dev.syntax.step01_overview;

public class Main2 {
	public static void main(String[] args) {
		System.setOut(new CustomPrintStream(System.out, true));
		//2개의 스레드 생성
		
		//첫 번째 스레드는 true를 10번 출력
		Thread thread1 = new Thread(() -> {
			for(int i=0;i<10;i++) {
				System.out.println(true);
			}
		});
		//두 번째 스레드는 false를 10번 출력
		Thread thread2 = new Thread(() -> {
			for(int i=0;i<10;i++) {
				System.out.println(false);
			}
		});
		// -> 2개의 스레드를 생성했고, 각 스레드가 수행할 작업을 콜백 함수 형태로 전달
		
		// 스레드 실행
		thread1.start();
		thread2.start();
	}
}
