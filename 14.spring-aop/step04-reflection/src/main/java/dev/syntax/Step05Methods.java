package dev.syntax;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Step05Methods {

	public static void main(String[] args) {
		
		Class<?> mouseClass = Mouse.class;
		
		Method[] methods = mouseClass.getDeclaredMethods();
		// Method도 getMethods()와 getDeclaredMethods()가 있음
		
		for (Method method : methods) {
			System.out.println(method.getName() + " - " + Arrays.toString(method.getParameterTypes()));
		}
	}
	// 테스팅 프레임워크에서 @Test가 붙은 메서드를 실행하기 위해 동적으로 메서드를 호출할 때 활용됨
}
