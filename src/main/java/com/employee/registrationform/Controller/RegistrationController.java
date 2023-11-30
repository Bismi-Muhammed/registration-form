package com.employee.registrationform.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.registrationform.Entity.Department;
import com.employee.registrationform.Entity.Employee;
import com.employee.registrationform.Service.EmployeeRegService;

@Controller
public class RegistrationController {

	@Autowired
	EmployeeRegService empService;


	//  method to get Registration page on login and to fetch department details into
	//  drop down on the frontend

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showForm(Model model) {

		List<Department> departments = empService.findAllDepartment();
		model.addAttribute("departments", departments);
		return "registration";
	}


//	  Post mapping to save the employee registration details into table Using
//	  request parameters and after saving details return a registration successful page	 

	@PostMapping("/saveEmployee")
	public String registerEmployee(@Valid @RequestParam String empName, @RequestParam String employeeNo,
			@RequestParam String joiningDate, @RequestParam Integer department, @RequestParam BigDecimal salary) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(joiningDate, formatter);

		Employee employee = new Employee();
		employee.setEmpNo(employeeNo);
		employee.setEmpName(empName);
		employee.setJoiningDate(localDate);
		employee.setDepartment(department);
		employee.setSalary(salary);
		empService.addRegistrationDetails(employee);
		return "success";
	}

}
