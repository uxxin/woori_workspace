package dev.syntax;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Test02DietPlannerTest {
  
  private DietPlanner dietPlanner;
  
  @BeforeEach // 각 @Test(테스트 케이스)를 실행하기 전에 매번 호출
  void setup() {
    this.dietPlanner = new DietPlanner(20, 30, 50);
  }

  @Test
  @DisplayName("적절한 코더 객체가 전달될 경우, 정확한 다이어트 플랜이 반환된다.")
  void test() {
    // given
    Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
    DietPlan expected = new DietPlan(2202, 110, 73, 275);
    
    // when
    DietPlan actual = dietPlanner.calculateDiet(coder);
    
    // then
    // assertEquals() // 주소값을 비교하기 때문에 테스트 실패
    
    // 객체 내 필드값들을 각각 비교
    assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
        () -> assertEquals(expected.getProtein(), actual.getProtein()),
        () -> assertEquals(expected.getFat(), actual.getFat()),
        () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));
  }

}
