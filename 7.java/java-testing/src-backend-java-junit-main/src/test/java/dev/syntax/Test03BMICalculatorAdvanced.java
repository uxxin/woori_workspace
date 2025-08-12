package dev.syntax;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
/**
 * 파라미터화된 테스트
 * @ValueSource
 * @CsvSource
 * ...
 */

class Test03BMICalculatorAdvanced {

	@ParameterizedTest // 파라미터화된 테스트
	@ValueSource(doubles= {89.0, 95.0, 110.}) // 이 값이 coderWeight에 전달됨.
	@DisplayName("전달된 파라미터의 값이 다이어트가 추천되는 정도일 경우, True를 반환한다.")
	void 전달된_파라미터의_값이(double coderWeight) {
		// Given
		double weight = coderWeight;
		double height = 1.72;

		// When
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// Then
		assertTrue(recommended); // recommended값이 true일 것이다.
	}
	
	@ParameterizedTest(name = "weight ={0}, height={1}") // 네이밍 테스트 추가
	@CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
	@DisplayName("전달된 파라미터의 값이 다이어트가 추천되는 정도일 경우, True를 반환한다.")
	void test2(double coderWeight, double coderHeight) {
		// Given
		double weight = coderWeight;
		double height = coderHeight;

		// When
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// Then
		assertTrue(recommended); // recommended값이 true일 것이다.
	}
	
	// src /test/resources에 있는 . csv 파일을 읽어서 테스트를 수행할 수 있게 작성
	// CsvFileSource 활용
	@ParameterizedTest
	@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1) //첫째줄은 속성명
	@DisplayName("csv 테스트")
	void test3(double coderWeight, double coderHeight) {
		// Given
		double weight = coderWeight;
		double height = coderHeight;

		// When
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// Then
		assertTrue(recommended); // recommended값이 true일 것이다.
	}
}
