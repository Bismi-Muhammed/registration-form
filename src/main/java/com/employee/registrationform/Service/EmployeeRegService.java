package com.employee.registrationform.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.registrationform.Entity.Department;
import com.employee.registrationform.Entity.Employee;
import com.employee.registrationform.Repository.DepartmentRepository;
import com.employee.registrationform.Repository.EmployeeRepository;
import com.employee.registrationform.exception.EmptyInputException;
import com.employee.registrationform.exception.ServiceLayerException;

@Service
public class EmployeeRegService {

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	public List<Department> findAllDepartment() {
		return departmentRepo.findAll();
	}

	public Employee addRegistrationDetails(Employee employee) {
		Employee savedEmp = employeeRepo.save(employee);
		return savedEmp;
	}

	public Employee saveEmployeeDetails(Employee employee) {
		if (employee.getEmpName().isEmpty() || employee.getEmpName().length() == 0) {
			throw new EmptyInputException("Input fields are empty");
		}
		Employee savedEmployee = employeeRepo.save(employee);
		return savedEmployee;
	}

	public Employee searchEmpByNumber(String empNo) {
		Optional<Employee> FetchEmployee = employeeRepo.findByEmpNo(empNo);
		return FetchEmployee.get();
	}

	public Employee searchEmpByName(String empName) {

		Employee FetchEmployee = employeeRepo.findByEmpName(empName);
		if (FetchEmployee == null) {
			throw new NoSuchElementException("Data doesnt existt in DB.It's database layer issue");
		}
		return FetchEmployee;
	}

	public Employee fetchEmpById(Long id) {
		Optional<Employee> FetchEmployee = employeeRepo.findById(id);
		return FetchEmployee.get();
	}

	public List<Employee> fetchAllEmployees() {
		List<Employee> employeeList = employeeRepo.findAll();
		if (employeeList.isEmpty()) {
			throw new ServiceLayerException("List is completely empty,No employee data exist in database");
		}

		return employeeList;
	}

	public void deleteEmployeeById(Long id) {
		employeeRepo.deleteById(id);

	}

	public List<Employee> employeesByDepartment(int id) {
		List<Employee> employeeList = employeeRepo.findEmpByDepartment(id);
		return employeeList;
	}

	public Employee findEmployeeById(Long id) {
		Optional<Employee> currentEmployee = employeeRepo.findById(id);
		return currentEmployee.get();
	}

	public Employee updateEmployee(Employee currentEmployee) {
		Employee updatedEmployee = employeeRepo.save(currentEmployee);
		return updatedEmployee;
	}

}
