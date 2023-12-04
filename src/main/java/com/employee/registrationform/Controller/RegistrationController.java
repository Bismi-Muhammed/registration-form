package com.employee.registrationform.Controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.employee.registrationform.Entity.Department;
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
// method to call success page upon registration completion
	@RequestMapping(value="/success",method = RequestMethod.GET)
	public String successPage() {
		return "success";
	}



}
