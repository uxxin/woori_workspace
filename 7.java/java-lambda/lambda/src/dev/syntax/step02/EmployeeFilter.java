package dev.syntax.step02;

import dev.syntax.model.Employee;

public interface EmployeeFilter {
	/**
	 * Employee 객체를 전달받아 필터링을 수행하는 추상 메서드
	 * @param employee
	 * @return 필터링 여부
	 */
	boolean filterEmployee(Employee employee);
}
