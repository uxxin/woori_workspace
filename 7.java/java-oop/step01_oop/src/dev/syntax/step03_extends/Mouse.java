package dev.syntax.step03_extends;

public class Mouse extends Animal {
	// Mouse는 Animal 클래스를 '확장'했다.
	// age와 color 필드를 Animal 클래스로부터 물려받음
	
//	int age;
//	String color;
//	-> 상위 클래스로부터 물려받은 필드들
	String address; // Mouse만이 가진 특성(Dog, Cat 은 가지고 있지 않음.)

	public Mouse(int age, String color) {
		//super(); // super () == Animal ()와 동일 
		super(age, color); // Animal(age, color)와 동일
	}

	public Mouse(int age, String color, String address) {
		super(age, color);
		this.address = address; //자신의 필드는 자신의 클래스에서 초기화
	}
	
}
