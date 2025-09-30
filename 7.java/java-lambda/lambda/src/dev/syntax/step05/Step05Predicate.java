package dev.syntax.step05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import dev.syntax.model.Designation;
import dev.syntax.model.Employee;
import dev.syntax.model.Skill;
import dev.syntax.model.Unit;
import dev.syntax.step02.EmployeeFilter;

/**
 * 필터 구현체를 별도의 파일(FSFilter, MSFilter..)로 생성하지 않고,
 * 이름이 없는 익명 클래스를 통해 인라인으로 바로 작성 후 활용하는 방식
 */
public class Step05Predicate {
  
  private static final List<Employee> employees = new ArrayList<>();

  public static void main(String[] args) {
    
    initialize(); // 기본 데이터셋 초기화
    
    // 금융팀(FS)에 속한 모든 사원 목록 조회
        System.out.println("== 금융팀 사원 목록 ==");
       // 람다 표현식
        // Predicate 인터페이스를 생성
        Predicate<Employee> fsFilter = (Employee employee) -> employee.getUnit() ==Unit.FS;
        
        List<Employee> employeesInFs = getAllEmployeesFilteredBy(fsFilter);
        System.out.println(employeesInFs);

//        // 마케팅팀(MS)에 속한 모든 사원 목록 조회
//        System.out.println("== 마케팅팀 사원 목록 ==");
//        List<Employee> employeesInMs = getAllEmployeesFilteredBy(msFilter);
//        System.out.println(employeesInMs);
//
//        // 모든 자바 개발자 목록 조회
//        System.out.println("== 자바 개발자 목록 ==");
//        List<Employee> allJavaDevelopers 
//        System.out.println(allJavaDevelopers);
//
//        // 모든 시니어 개발자 목록 조회
//        System.out.println("== 10년 이상 시니어 개발자 목록 ==");
//        List<Employee> allHavingGreaterThan 
//        System.out.println(allHavingGreaterThan);
  }
  
  private static List<Employee> getAllEmployeesFilteredBy(Predicate<Employee>filter) {
    List<Employee> filteredEmployees = new ArrayList<>();
    for (Employee employee : employees) {
      if (filter.test(employee)) {
        filteredEmployees.add(employee);
      }
    }
    
    return filteredEmployees;
  }
  
  
  public static void initialize() {
        List<Skill> dev1Skills = new ArrayList<>();
        dev1Skills.add(Skill.JAVA);
        dev1Skills.add(Skill.JPA);

        employees.add(new Employee("이나무", 8, Designation.DEVELOPER, Unit.FS, dev1Skills));

        List<Skill> dev2Skills = new ArrayList<>();
        dev2Skills.add(Skill.MICROSOFT);
        employees.add(new Employee("유아리", 5, Designation.DEVELOPER, Unit.FS, dev2Skills));

        List<Skill> dev3Skills = new ArrayList<>();
        dev3Skills.add(Skill.PYTHON);
        employees.add(new Employee("하버들", 7, Designation.DEVELOPER, Unit.FS, dev3Skills));

        List<Skill> dev4Skills = new ArrayList<>();
        dev4Skills.add(Skill.ANGULARJS);
        dev4Skills.add(Skill.JAVASCRIPT);
        employees.add(new Employee("권나길", 3, Designation.DEVELOPER, Unit.MS, dev4Skills));

        List<Skill> dev5Skills = new ArrayList<>();
        dev5Skills.add(Skill.ANGULARJS);
        dev5Skills.add(Skill.JAVA);
        employees.add(new Employee("오다운", 3, Designation.DEVELOPER, Unit.FS, dev5Skills));

        List<Skill> managerSkills = new ArrayList<>();
        managerSkills.add(Skill.PMP);
        employees.add(new Employee("윤믿음", 15, Designation.MANAGER, Unit.FS, managerSkills));

        List<Skill> architectSkills = new ArrayList<>();
        architectSkills.add(Skill.DESIGN);
        employees.add(new Employee("강한울", 13, Designation.ARCHITECT, Unit.FS, architectSkills));
    }

}
