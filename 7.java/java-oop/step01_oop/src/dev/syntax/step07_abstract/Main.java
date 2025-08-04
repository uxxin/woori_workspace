package dev.syntax.step07_abstract;

public class Main {
	public static void main(String[] args) {
//		Animal a = new Animal(); // 추상 클래스이기 때문에 인스턴스로 생성 불가능해짐.
		//a.sing();
		
		Dog d = new Dog();
		//d.sing(); //별도로 오버라이딩하지 않았기 때문에 Animal의 동작이 그대로 수행됨. 
		
		Cat c = new Cat();
		//c.sing();
		
		//Animal 타입만 받는 Animal 배열
		Animal[] animals = { d, c };
		
		for(Animal animal : animals) {
			animal.sing();
		}
	}
}
