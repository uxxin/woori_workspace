package dev.syntax.step02_constructor;

public class Mouse {
	
	// 필드
	int age;
	String name;
	
	//일련의 메서드처럼 정의해본 것
	Mouse(){ //기본 생성자(default constructor) - 기본 생성자 
	
		System.out.println("Mouse() 메서드가 호출됨");
	}
	// 기본생성자는 개발자가 별도의 생성자를 직접 작성하지 않으면 컴파일러가 런타임에 알아서 추가해 줌.
	
	//매개변수(주로 필드)가 있는 생성자
	Mouse(int age){
		System.out.println(this); // 인스턴스 
		this.age = age; //파라미터로 전달받은 age 값을 Mouse 클래스의 필드에 초기화
		
	}
}
