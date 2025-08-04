package dev.syntax.step01_class;

//Main이라는 이름의 클래스는 객체 지향 맥락에서의 클래스가 아닌
// 프로그램을 실행하기 위한 파일
public class Main {
	// 프로그램 실행은 JVM이 main() 메서드를 호출하면서 시작됨. 
	//main+Ctrl+Space
	public static void main(String[] args) {
		//설계했던 Mouse 클래스를 new 연산자를 통해 쥐를 생성
		System.out.println(new Mouse());
		//ctrl+f11로 실행
		
		// 생성한 쥐 객체(인스턴스)를 jerry라는 참조 변수에 할당
		Mouse jerry = new Mouse();
		
		//jerry의 나이 조회
		System.out.println(jerry.age);

		jerry.age = 5;
		System.out.println(jerry.age);
		
		System.out.println(jerry.name);
	}

}
