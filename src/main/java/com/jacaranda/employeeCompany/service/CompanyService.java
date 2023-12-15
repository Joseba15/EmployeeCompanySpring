package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository repository;
	
	public List<Company> getCompanies(){
		return repository.findAll();
		
	}
	
	public Company getCompany(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	
	public void addCompany (Company item) {
		if (item.getName() != null && item.getCity()!=null && item.getAddress()!=null  ) {
			repository.save(item);
			
		}
		
	}
	
	
	public Company updateCompany (Company compMod) {
		
		Company comp = null;
		
		if (compMod.getName() != null && compMod.getCity()!=null && compMod.getAddress()!=null  ) {
			
			if(this.getCompany(compMod.getId())==null) {
				
			}
			
			comp = this.getCompany(compMod.getId());

			comp.setName(compMod.getName());
			comp.setAddress(compMod.getAddress());
			comp.setCity(compMod.getCity());

			repository.save(comp);
		}
		
		return comp;
	}
	
	
	public boolean deleteCompany(Company item) {
		boolean deleted = false;
		
		if(this.getCompany(item.getId()) != null) {
			repository.delete(item);
			deleted = true;
		}
		
		return deleted;
	}
	
	
}
