package dev.syntax;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BMICalculatorTest {

	@Test
	@DisplayName("전달된 파라미터의 값이 다이어트가 추천되는 정도일 경우, True를 반환한다.")
	void 전달된_파라미터의_값이() {
		// Given
		double weight = 89.0;
		double height = 1.72;

		// When
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// Then
		assertTrue(recommended); // recommended값이 true일 것이다.
	}

	@Test
	@DisplayName("내가 해보는 다이어트 추천")
	void testMy1() {
		double weight = 100.0;
		double height = 1.66;
		
		boolean result = BMICalculator.isDietRecommended(weight, height);
		assertTrue(result);
		
	}
	
	@Test
	@DisplayName("전달된 파라미터 중 키가 0일 경우, ArithmeticException을 던진다.")
	void should_ThrowArithmeticException_When_HeightZero() {
		// Given
		double weight = 89.0;
		double height = 0.0;

		// When
		Executable executable = () -> BMICalculator.isDietRecommended(weight, 0.0);

		// Then
		assertThrows(ArithmeticException.class, executable);

	}
	
	@Test
	@DisplayName("내가해보는 ArthimeticException 던지기")
	void My_ThrowArithmeticException_When_HeightZero() {
		double weight = 50.0;
		double height = 0.0;
		
		Executable executable = () -> BMICalculator.isDietRecommended(weight, 0);
		assertThrows(ArithmeticException.class, executable);
	}
	// calculateBMI()는 private이라 접근 불가

	// findCoderWithWorstBMI() 메서드 테스트
	@Test
	@DisplayName("코더 목록이 비어있지 않을 경우, Worst BMI를 가진 코더를 반환한다.")
	void should_ReturnCoderWithWorstBMI_When_CoderListNotEmty() {
		// given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));

		// when
		Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

//	    assertEquals(1.82, coderWorstBMI.getHeight());
//	    assertEquals(98.0, coderWorstBMI.getWeight()); //테스트되지 않음 

		// 2개 이상의 단정문 테스트
		assertAll(() -> assertEquals(1.82, coderWorstBMI.getHeight()),
				() -> assertEquals(98.0, coderWorstBMI.getWeight()));
	}
	
	// getBMIScores() 테스트
	  @Test
	  @DisplayName("getBMIScores()에 빈 배열이 전달되지 않을 경우, 정확한 BMI 점수 배열이 반환된다.")
	  void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
	    //given
		  List<Coder> coders = new ArrayList<>();
		  coders.add(new Coder(1.80, 60.0));
		  coders.add(new Coder(1.82, 98.0));
		  coders.add(new Coder(1.82, 64.7));
		//when
		  double[] expected = { 18.52, 29.59, 19.53 }; // 값 바꾸면서 실패하는지 확인
		  // 리턴값 적기
		  double[] bmiScores = BMICalculator.getBMIScores(coders);
		  
		//then
//		  assertEquals(expected, bmiScores); // 이렇게 하면 주소값이 비교된다. 
		  assertArrayEquals(expected, bmiScores);
	  }

}
