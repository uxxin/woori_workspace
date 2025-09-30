package dev.syntax;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Step07Invoking {

	public static void main(String[] args) {
		// Reflection은 단순히 클래스의 멤버에 대한 접근 뿐만 아니라 해당 멤버들을 조작할 수도 있도록 제공함

		// 리플렉션을 통한 메서드 호출(invoke)		
		Class<?> stringClass = String.class;
		try {
			// 1. Class 객체에서 제공하는 getMethod나 getDeclaredMethod를 통해 Method 객체 취득
			Method toUpperCaseMethod = stringClass.getMethod("toUpperCase");
			// 2. Method 객체에서 제공하는 invoke() 호출
			String result = (String) toUpperCaseMethod.invoke("hello");
			System.out.println(result);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
