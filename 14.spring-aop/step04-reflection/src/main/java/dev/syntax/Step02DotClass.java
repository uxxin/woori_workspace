package dev.syntax;

public class Step02DotClass {

	public static void main(String[] args) {
		
		// .class: 별도의 객체를 초기화하지 않고 Class 객체를 얻고 싶을 때 활용
		Class<?> clazz = String.class;
		System.out.println(clazz.getName()); // 클래스의 이름 확인 - java.lang.String
		
		Class<?> intClazz = int.class;
		System.out.println(intClazz.getName());
		
		Class<?> mouseClazz = Mouse.class;
		System.out.println(mouseClazz.getName()); // dev.syntax.mouse	
		
	}

}
