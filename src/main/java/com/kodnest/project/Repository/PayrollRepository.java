package com.kodnest.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.project.Entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long>{
	
	List<Payroll> findByEmployeeEmpId(Long empId);
	
	  Optional<Payroll> findTopByEmployeeEmpIdOrderByPayrollIdDesc(Long empId);
	  
	  

}
