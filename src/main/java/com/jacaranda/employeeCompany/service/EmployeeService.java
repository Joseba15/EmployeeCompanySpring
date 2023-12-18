package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.repository.EmployeeRepository;


@Service
public class EmployeeService {

	
	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> getEmployees(){
		return repository.findAll();
		
	}
	
	public Employee getEmployee(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	
	public void addEmployee (Employee item) {
		if (item.getFirstName() != null && item.getLastName()!=null && item.getDateOfBirth()!=null
				&& item.getEmail()!=null  && item.getGender()!=null && item.getCompany()!=null ) {
			repository.save(item);
			
		}
		
	}
	
	
	public Employee updateEmployee (Employee compMod) {
		
		Employee emp = null;
		
//		if (compMod.getName() != null && compMod.getCity()!=null && compMod.getAddress()!=null  ) {
//			
//			if(this.getCompany(compMod.getId())==null) {
//				
//			}
//			
//			comp = this.getCompany(compMod.getId());
//
//			comp.setName(compMod.getName());
//			comp.setAddress(compMod.getAddress());
//			comp.setCity(compMod.getCity());
//
//			repository.save(comp);
//		}
		
		return emp;
	}
	
	
}
