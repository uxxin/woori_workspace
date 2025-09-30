package dev.syntax.step02;

import dev.syntax.model.Employee;
import dev.syntax.model.Unit;

// EmployeeFilter 인터페이스 구현체
// -> 금융팀 사원만 필터링 
public class FSFilter implements EmployeeFilter {

  // Alt + Shift + s
  @Override
  public boolean filterEmployee(Employee employee) {
    return employee.getUnit() == Unit.FS; // 핵심 로직만 가져왔음
  }
  
}
