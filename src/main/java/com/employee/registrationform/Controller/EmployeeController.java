package com.employee.registrationform.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.registrationform.Entity.Employee;
import com.employee.registrationform.Service.EmployeeRegService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRegService employeeRegService;

//	  Post mapping to save the employee registration details into table Using
//	  request parameters and after saving details return a registration successful page	 

	@PostMapping("/saveEmployee")
	public ResponseEntity<String> registerEmployee(@Valid @RequestParam String empName, @RequestParam String employeeNo,
			@RequestParam String joiningDate, @RequestParam Integer department, @RequestParam BigDecimal salary) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String[] date=joiningDate.split("/");
		String currentDate=date[1]+"/"+date[0]+"/"+date[2];
		LocalDate localDate = LocalDate.parse(currentDate, formatter);
		Employee employee = new Employee();
		employee.setEmpNo(employeeNo);
		employee.setEmpName(empName);
		employee.setJoiningDate(localDate);
		employee.setDepartment(department);
		employee.setSalary(salary);
		Employee savedEmployee = employeeRegService.addRegistrationDetails(employee);
		String successMessage = "Registration successful for user: " + savedEmployee.getEmpName();
		return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);

	}

	// Post ampping to save employee registration details using request body

	@PostMapping("/addRegistration")
	public ResponseEntity<Employee> addRegistration(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeRegService.saveEmployeeDetails(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	// rest api to search employees based on their employee number

	@GetMapping("searchEmployeeByNumber/employeeNo/{empNo}")
	public ResponseEntity<Employee> getEmployeeByNumber(@PathVariable String empNo) {
		Employee fetchedEmp = employeeRegService.searchEmpByNumber(empNo);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}

	// rest api to search employee based on name

	@GetMapping("searchEmployeeByName/employeeName/{empName}")
	public ResponseEntity<Employee> getEmployeeByName(@PathVariable String empName) {
		Employee fetchedEmp = employeeRegService.searchEmpByName(empName);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}

	// This method retrieves employee details by their Id

	@GetMapping("searchEmployeeById/employeeId/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee fetchedEmp = employeeRegService.fetchEmpById(id);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}

	// This method retrieves list of all employee registartion details

	@GetMapping("/fetchAllEmployees")
	public ResponseEntity<List<Employee>> fetchAllEmployees() {
		List<Employee> employeeList = employeeRegService.fetchAllEmployees();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	// This method delete a particular employee by Id

	@DeleteMapping("/deleteEmployeeById/id/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		employeeRegService.deleteEmployeeById(id);
	}

	// This methos retrieves list of employees under a department

	@GetMapping("/searchEmployeeByDepartmentName/departmentName/{DepartmentName}")
	public ResponseEntity<?> employeesByDepartment(@PathVariable String DepartmentName) {
		List<Employee> employeeList = employeeRegService.getEmployeesByDepartmentName(DepartmentName);
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	// This method update a particular employee details by their Id

	@PutMapping("/updateEmployeeDetails/id/{id}")
	public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable Long id, @RequestBody Employee employee) {
		Employee currentEmployee = employeeRegService.findEmployeeById(id);
		System.out.println(currentEmployee);
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
