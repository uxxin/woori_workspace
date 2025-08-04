package dev.syntax.step07_abstract;

public abstract class Animal {
//	void sing() {
//		System.out.println("동물은 어떻게 울지?");
//	}
	abstract void sing();
	//Main.java에서 Animal a = new Animal();을 해서 인스턴스 생성 후,
	//a.sing()을 호출할 경우 정상 동작해야 하기 때문에 컴파일 에러가 발생한 상황
	
	/*메서드 앞에 abstract를 붙이면 해당 메서드는 추상 메서드이기 때문에
	 * 별도의 구현을 하지 않겠다고 명시
	 * 
	 * abstract 적용 후 발생한 에러
	 * The abstract method sing in type Animal can only be defined by an abstract class
	 */
}
