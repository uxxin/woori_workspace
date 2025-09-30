package dev.syntax.step02_create_thread;

public class Ex01 {
	public static void main(String[] args) {
		//3. 스레드 객체 생성
		FirstThread firstThread = new FirstThread();
		//4. 스레드 실행(run()메서드를 호출시키는 작업)
		firstThread.start(); //바로 firstThread.run()으로 직접 호출하면 안된다 !! 
	}
}
