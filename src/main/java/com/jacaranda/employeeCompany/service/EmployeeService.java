package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.repository.EmployeeRepository;


@Service
public class EmployeeService {

	
	@Autowired
	EmployeeRepository repository;
	
	
	public Page<Employee> getEmployeesPageable(int pageNum, int pageSize){
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize);
		return repository.findAll(pageable);
		
	}
	
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
	
	
	public Employee updateEmployee (Employee empMod) throws Exception {
		
		Employee emp = null;
		
		if (empMod.getFirstName() != null && empMod.getLastName()!=null && empMod.getDateOfBirth()!=null
				&& empMod.getEmail()!=null  && empMod.getGender()!=null && empMod.getCompany()!=null ) {
	
			
			if(this.getEmployee(empMod.getId())==null) {
				throw new Exception();
			}
			
			emp = this.getEmployee(empMod.getId());

			emp.setFirstName(empMod.getFirstName());;
			emp.setLastName(empMod.getLastName());
			emp.setEmail(empMod.getEmail());
			emp.setGender(empMod.getGender());
			emp.setCompany(empMod.getCompany());
			emp.setDateOfBirth(empMod.getDateOfBirth());
			
			repository.save(emp);
		}
		
		
		
		return emp;
	}
	
	
}
