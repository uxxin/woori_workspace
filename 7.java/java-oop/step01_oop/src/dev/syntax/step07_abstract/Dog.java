package dev.syntax.step07_abstract;

//Dog를 Animal 클래스의 구현체, 구현 클래스(Implementation)라고 함.
public class Dog extends Animal{

	@Override
	void sing() {
//		super.sing();//상위 클래스(Animal)의 sing()을 호출
		System.out.println("멍멍");
	}
	//void sing()을 상속받은 상태
	
}
