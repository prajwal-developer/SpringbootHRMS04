package com.kodnest.project.Controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Entity.Payroll;
import com.kodnest.project.Service.PayrollService;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/payroll")
@CrossOrigin(origins = "*")
public class PayrollController {
	
	 @Autowired
	    private PayrollService payrollService;

	    @PostMapping
	    public Payroll createPayroll(@RequestBody Payroll payroll) {
	        return payrollService.createPayroll(payroll);
	    }

	    @GetMapping("/{id}")
	    public Payroll getPayroll(@PathVariable("id") Long payrollId)
	    {
	        return payrollService.getPayrollById(payrollId);
	    }
	    
	    @GetMapping
	    public List<Payroll> getAllPayrolls() {
	        return payrollService.getAllPayrolls();
	    }

	   

	    @DeleteMapping("/{id}")
	    public String deletePayroll(@PathVariable Long id) {
	        payrollService.deletePayroll(id);
	        return "Payroll Deleted Successfully";
	    }
}
