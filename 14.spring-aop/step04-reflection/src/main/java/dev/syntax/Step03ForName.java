package dev.syntax;

public class Step03ForName {

	public static void main(String[] args) {
		
		// .class: FQCN(Fully Qualified Class Name)을 통해 동적으로 클래스를 로딩하고 싶을 때 활용
		Class<?> clazz;
		try {
			clazz = Class.forName("java.util.List"); 
			System.out.println(clazz.getName()); // 클래스의 이름 확인 - java.lang.String
			System.out.println(clazz.getSimpleName());// 패키지 제외, 클래스 이름만 확인 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	// 과거 JDBC에서 드라이버를 동적으로 로딩할 때 사용되던 방식
}
