package com.jacaranda.employeeCompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.service.CompanyService;
import com.jacaranda.employeeCompany.service.EmployeeService;


@Controller
public class EmployeeController {

	
	@Autowired EmployeeService service;
	
	@Autowired CompanyService compService;
	
	
	@GetMapping({"/","/employee/listPageable"})
	public String listEmployeePageable (Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
			@RequestParam("sizeNumber") Optional<Integer> sizeNumber, @RequestParam("sortField") Optional<String> sortField, 
			@RequestParam("order") Optional<Integer> order ) {
		

		
		Page<Employee> page = service.getEmployeesPageable(pageNumber.orElse(1), sizeNumber.orElse(10),sortField.orElse("id") );
		
		model.addAttribute("currentPage", pageNumber.orElse(1) );
		model.addAttribute("totalItems", page.getTotalElements() );
		model.addAttribute("totalPages", page.getTotalPages() );
		model.addAttribute("list", page.getContent());


		
		return  "listEmployee";
	}
	
	
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
		
		return  "addEmployee";
	}
	
	
	@PostMapping("/employee/add/submit")
	public String addEmployeeSubmit(@Validated @ModelAttribute("emp") Employee e, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "addEmployee";
		}
		
		service.addEmployee(e);	
		
		return "redirect:/employee/list";
	}
	
	
	@GetMapping("/employee/update")
	public String editCompany (@RequestParam  String id ,Model model) {
		
		Integer idInt =-1;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			e.getMessage();
		}
		
		Employee e =  service.getEmployee(idInt);
		
		model.addAttribute("emp",e);
		
		return  "editEmployee";
	}
	
	
	@PostMapping("/employee/update/submit")
	public String updateEmployeeSubmit(@Validated @ModelAttribute("emp") Employee e, BindingResult bindingResult) throws Exception {
			
		if (bindingResult.hasErrors()) {
			return "editEmployee";
		}
		
		service.updateEmployee(e);	
		
		return "redirect:/employee/list";
	}

}
