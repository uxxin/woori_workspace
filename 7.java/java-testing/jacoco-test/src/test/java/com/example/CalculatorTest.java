package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSub() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.sub(3, 2));
    }

    // mul()과 div()는 테스트하지 않음 → 커버리지 리포트에서 미커버로 표시됨
}
