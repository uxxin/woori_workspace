package dev.syntax.step02;

import dev.syntax.model.Employee;
import dev.syntax.model.Unit;

public class MSFilter implements EmployeeFilter {

	@Override
	public boolean filterEmployee(Employee employee) {
		return employee.getUnit() == Unit.MS;
	}
	
}
