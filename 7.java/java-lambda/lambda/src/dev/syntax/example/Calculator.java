package dev.syntax.example;

// 하나의 인터페이스만 다룰 수 있게 선던됨( 함수형 인터페이스 어노테이션)
@FunctionalInterface
public interface Calculator {
	int add(int a, int b);
//	int sub(int a, int b); => 이거 안되도록 막아주는게 함수형 인터페이스 어노테이션 역할 
	}
