package com.employee.registrationform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.registrationform.Entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department getDepartmentByDepartmentName(String departmentName);
}
