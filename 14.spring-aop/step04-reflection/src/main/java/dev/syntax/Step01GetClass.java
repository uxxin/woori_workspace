package dev.syntax;

public class Step01GetClass {

	public static void main(String[] args) {
		String s = "Hello, Reflection";
		
		
		Class<?> clazz = s.getClass(); // java.lang.Object 클래스로부터 상속받은 getClass() 호출
		// 인스턴스(s)에 대한 런타임 클래스 타입을 나타내는 Class 타입을 반환함
		
		System.out.println(clazz.getName()); // 클래스의 이름 확인 - java.lang.String
		
		Mouse m = new Mouse();
		Class<?> mouseClazz = m.getClass();
		System.out.println(mouseClazz.getName()); // dev.syntax.mouse	
		
		// 직관적으로 Class의 정보를 취득할 수 있음
	}

}
