package com.employee.registrationform.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.registrationform.Entity.Employee;
import com.employee.registrationform.Service.EmployeeRegService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRegService employeeRegService;


 //  Post ampping to save employee registration details using request body	
	
	@PostMapping("/addRegistration")
	public ResponseEntity<Employee> addRegistration(@Valid @RequestBody Employee employee){
		Employee savedEmployee=employeeRegService.saveEmployeeDetails(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	 
	// rest api to search employees based on their employee number
	
	@GetMapping("searchEmployeeByNumber/employeeNo/{empNo}")
	public ResponseEntity<Employee> getEmployeeByNumber(@PathVariable String empNo) {
		Employee fetchedEmp = employeeRegService.searchEmpByNumber(empNo);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}
	
	//rest api to search employee based on name

	@GetMapping("searchEmployeeByName/employeeName/{empName}")
	public ResponseEntity<Employee> getEmployeeByName(@PathVariable String empName) {
		Employee fetchedEmp = employeeRegService.searchEmpByName(empName);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}
	
	// rest api to get employee details by their Id

	@GetMapping("searchEmployeeById/employeeId/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee fetchedEmp = employeeRegService.fetchEmpById(id);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}
	
	// get mapping to get list of all employee registartion details

	@GetMapping("/fetchAllEmployees")
	public ResponseEntity<List<Employee>> fetchAllEmployees() {
		List<Employee> employeeList = employeeRegService.fetchAllEmployees();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	// rest api to delete a particular employee by Id

	@DeleteMapping("/deleteEmployeeById/id/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		employeeRegService.deleteEmployeeById(id);
	}

	
	// rest api to get list of employees  under a department
	@GetMapping("/searchEmployeeByDepartment/departmentId/{id}")
	public ResponseEntity<List<Employee>> employeesByDepartment(@PathVariable int id) {
		List<Employee> employeeList = employeeRegService.employeesByDepartment(id);
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	
	// rest api to update a particular employee details by their Id
	@PutMapping("/updateEmployeeDetails")
	public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable Long id, @RequestBody Employee employee) {
		Employee currentEmployee = employeeRegService.findEmployeeById(id);
		if (currentEmployee == null) {
			return ResponseEntity.notFound().build();
		}
		currentEmployee.setEmpNo(employee.getEmpNo());
		currentEmployee.setEmpName(employee.getEmpName());
		currentEmployee.setJoiningDate(employee.getJoiningDate());
		currentEmployee.setDepartment(employee.getDepartment());
		currentEmployee.setSalary(employee.getSalary());
		Employee savedEmployee = employeeRegService.updateEmployee(currentEmployee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
	}

}
