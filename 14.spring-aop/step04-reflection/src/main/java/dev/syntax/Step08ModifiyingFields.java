package dev.syntax;

import java.lang.reflect.Field;

public class Step08ModifiyingFields {

	public static void main(String[] args) {
		Mouse jerry = new Mouse();
		jerry.setName("제리");
		
		Class<?> mouseClass = Mouse.class;
		
		try {
			Field mouseNameField = mouseClass.getDeclaredField("name");
			mouseNameField.setAccessible(true);
			String mouseName = (String) mouseNameField.get(jerry);
			System.out.println(mouseName);
			
			mouseNameField.set(jerry, "제이미");
			String mouseNamae = (String) mouseNameField.get(jerry);
			System.out.println(mouseNamae);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
