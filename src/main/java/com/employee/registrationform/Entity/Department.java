package com.employee.registrationform.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 10, message = "Employee Number must be at most 10 characters")
	@Pattern(regexp = "\\d+", message = "Value should contain only numeric characters")
	@Column(name = "employee_no")
	private String empNo;

	@Size(max = 100, message = "Employee Nmae must be at most 100 characters")
	@Column(name = "employee_name")
	private String empName;

	@Column(name = "date_of_joining")
	private LocalDate joiningDate;

	@Column(name = "department")
	private int department;

	@DecimalMin(value = "0", inclusive = true, message = "Value must be greater than or equal to 0")
	@DecimalMax(value = "9999999999", inclusive = true, message = "Value must have a maximum of 10 digits")
	private BigDecimal salary;

	public Employee() {

	}

	public Employee(Long id, String empNo, String empName, LocalDate joiningDate, int department, BigDecimal salary) {
		super();
		this.id = id;
		this.empNo = empNo;
		this.empName = empName;
		this.joiningDate = joiningDate;
		this.department = department;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empNo=" + empNo + ", empName=" + empName + ", joiningDate=" + joiningDate
				+ ", department=" + department + ", salary=" + salary + "]";
	}

}
