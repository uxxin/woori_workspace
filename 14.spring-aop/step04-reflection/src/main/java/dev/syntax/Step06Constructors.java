package dev.syntax;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Step06Constructors {

	public static void main(String[] args) {
		
		Class<?> mouseClass = Mouse.class;
		
		Constructor<?>[] constructors = mouseClass.getDeclaredConstructors();
		// Method도 getMethods()와 getDeclaredMethods()가 있음
		
		for (Constructor<?> constructor : constructors) {
		    System.out.println(constructor.getName() + " - " + Arrays.toString(constructor.getParameterTypes()));
		}
	}
	// DI 프레임워크에서 설정 파일이나 Annotation 기반 의존성 주입 시 활용됨 
}
