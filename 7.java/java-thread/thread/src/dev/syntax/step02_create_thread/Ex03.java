package dev.syntax.step02_create_thread;

// 3. 스레드 객체를 생성하는 방법 세 번째, 람다 표현식, 함수형 인터페이스를 활용
// 별도의 SecondThread 이런 클래스를 만들 필요가 없다. 
public class Ex03 {
	public static void main(String[] args) {
		// 1. 스레드 객체 생성
		Runnable thirdThread = () -> { //별도의 클래스를 파일로 생성하지 않고, 인라인으로 작성
			//2. 스레드가 수행할 작업 작성 부분
			System.out.println("스레드 작업 시작");
			System.out.println("생성된 스레드 ID: "+Thread.currentThread().getId());
		};
		
		Thread thread = new Thread(thirdThread);
		
		//3. 스레드 실행
		thread.start();
	}
}
