package dev.syntax;

import java.lang.reflect.Field;

public class Step04Fields {

	public static void main(String[] args) {
		// Reflection에서 제공해주는 API를 통해 필드에 접근할 수 있음
		Class<?> mouseClass = Mouse.class;
		
		Field[] fieldsForPublic = mouseClass.getFields(); // public 필드만 가져옴
		
		for (Field field : fieldsForPublic) {
			System.out.println(field.getName() + " - " + field.getType());
		}
	
		System.out.println("========================");
		
		Field[] allFields = mouseClass.getDeclaredFields(); // private까지 포함한 모든 필드를 가져옴
		
		for (Field field : allFields) {
			System.out.println(field.getName() + " - " + field.getType());
		}
		// 이러한 필드 접근은 ORM에서 엔터티와 Java 객체를 맵핑할 때 정확하게 맵핑되었는지 확인할 때 활용될 수 있음
	}
}
