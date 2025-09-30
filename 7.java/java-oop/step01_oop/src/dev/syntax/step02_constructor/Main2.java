package dev.syntax.step02_constructor;

public class Main2 {
	public static void main(String[] args) {
		//Mouse()라는 메서드를 Mouse.java에 정의하지 않았을 떄도 프로그램은 잘 동작했음.
		Mouse jerry = new Mouse();
		System.out.println(jerry);
		//값을 초기화하는 방법 첫 번째 - 필드에 직접 접근해서 초기화
		jerry.age = 5;
		
		//객체를 생성할 때 new 연산자 뒤에 작성하는 메서드를 생성자라고 함. 
		Mouse mickey = new Mouse(90);
		System.out.println(mickey);
		System.out.println(mickey.age);
		
		// 값을 초기화하는 방법 두 번째 - 매개변수가 있는 생성자를 통해 초기화
		// new Mouse(90);
	}
}
