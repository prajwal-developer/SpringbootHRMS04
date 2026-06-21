package com.kodnest.project.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Entity.Payroll;
import com.kodnest.project.Repository.EmployeeRepository;
import com.kodnest.project.Repository.PayrollRepository;

@Service
public class PayrollService {
	
	@Autowired
	private PayrollRepository payrollRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public Payroll createPayroll(Payroll payroll)
	{
		
		 Employee employee = employeeRepo.findById(
		            payroll.getEmployee().getEmpId())
		            .orElseThrow(() ->
		                    new RuntimeException("Employee not found"));

		    payroll.setEmployee(employee);

		
		return payrollRepo.save(payroll);
		
	}
	
	public Payroll getPayrollById(Long payrollId)
	{
		return payrollRepo.findById(payrollId) .orElseThrow(() -> new RuntimeException( "Payroll not found"));
		
	}
	
	public List<Payroll> getAllPayrolls() 
	{
		return payrollRepo.findAll(); 
		
	}
	
	public List<Payroll> getPayrollByEmployeeId( Long employeeId) 
    {
			return payrollRepo.findByEmployeeEmpId(employeeId); 
	}		
		
     
		public void deletePayroll(Long payrollId) 
		{
			Payroll payroll = getPayrollById(payrollId); payrollRepo.delete(payroll);
			
		}

}
