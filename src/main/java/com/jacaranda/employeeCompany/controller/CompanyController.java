package com.jacaranda.employeeCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired CompanyService service;
	
	@GetMapping("/listCompany")
	public String listCompanies (Model model) {
		List<Company> listCompanies = service.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		
		return  "listCompany";
	}
	
	
	
	@GetMapping("/addCompany")
	public String addCompany (Model model) {
		
		Company c = new Company();
		
		model.addAttribute("company",c );
		
		return  "addCompany";
	}
	
	@PostMapping("/addCompany/submit")
	public String addCompanySubmit(@ModelAttribute("company") Company c) {
		
		
		service.addCompany(c);	
		
		return "redirect:/listCompany";
	}
	
	
	
	@GetMapping("/editCompany")
	public String editCompany (@RequestParam  Integer id ,Model model) {
		
		Company c =  service.getCompany(id);
		
		model.addAttribute("company",c);
		
		return  "editCompany";
	}
	
	
	@PostMapping("/editCompany/submit")
	public String updateCompanySubmit(@ModelAttribute("company") Company c) {
			
		service.updateCompany(c);	
		
		return "redirect:/listCompany";
	}
	
	
	@GetMapping("/deleteCompany")
	public String deleteItem(@RequestParam Integer id ,Model model) {
		
		Company c =  service.getCompany(id);

		
		model.addAttribute("company",c);
		
		return "deleteCompany";
	}
	
	@PostMapping("/deleteCompany/submit")
	public String deleteItemSubmit(@ModelAttribute Company item) {
		
		service.deleteCompany(item);
		
		return "redirect:/listCompany";
	}
	
}
