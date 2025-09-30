package dev.syntax.step02;

import dev.syntax.model.Employee;
import dev.syntax.model.Skill;

public class JAVAFilter implements EmployeeFilter {

	@Override
	public boolean filterEmployee(Employee employee) {
		
		return employee.getSkills().contains(Skill.JAVA);
	}
	

}
