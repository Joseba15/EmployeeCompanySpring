package com.jacaranda.employeeCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.service.CompanyService;
import com.jacaranda.employeeCompany.service.EmployeeService;


@Controller
public class EmployeeController {

	
	@Autowired EmployeeService service;
	
	@Autowired CompanyService compService;
	
	@GetMapping("/employee/list")
	public String listEmployee (Model model) {
		List<Employee> listEmployee = service.getEmployees();
		model.addAttribute("list", listEmployee);
		
		return  "listEmployee";
	}
	
	
	@GetMapping("/employee/add")
	public String addEmployee (Model model) {
		
		Employee e = new Employee();
		model.addAttribute("emp",e );
		model.addAttribute("listComp", compService.getCompanies());
		
		return  "addCompany";
	}
	
	
	@PostMapping("/employee/add/submit")
	public String addEmployeeSubmit(@ModelAttribute("emp") Employee e) {
		
		
		service.addEmployee(e);	
		
		return "redirect://employee/list";
	}

}
