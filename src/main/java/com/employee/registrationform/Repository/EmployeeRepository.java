package com.employee.registrationform.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.registrationform.Entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmpNo(String empNo);

	Employee findByEmpName(String empName);

	List<Employee> findEmpByDepartment(int id);

}
