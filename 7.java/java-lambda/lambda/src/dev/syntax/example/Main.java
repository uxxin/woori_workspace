package dev.syntax.example;

public class Main {
	public static void main(String[] args) {
		//굳이 별도의 구현체를 클래스(CalculatorImplementation)로 생성하지 않고도
		// 함수 형태로 작성 가능하도록(람다 표현식)
		Calculator c1 = (a, b) -> a+b ;
	}
}
