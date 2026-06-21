package com.kodnest.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.project.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	 Optional<Employee> findByEmail(String email);

	    Optional<Employee> findByEmpId(Long empId);

	    boolean existsByEmail(String email);

	    boolean existsByEmpId(Long empId);
    
	    
	    
}
