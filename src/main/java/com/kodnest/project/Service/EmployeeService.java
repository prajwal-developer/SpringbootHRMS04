package com.kodnest.project.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	//Add Employee
	public Employee addEmployee(Employee employee)
	{
		validateAge(employee);
		
		if (employeeRepo.existsByEmail(employee.getEmail()))
		{
			throw new RuntimeException("Email already Exists");
		}
		
		return employeeRepo.save(employee);
	}
	
	// Get Employee By Id
	public Employee getEmployeeById(Long empId)
	{
		 return employeeRepo.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));
	}
	
	// Get All Employees
	public List<Employee> getAllEmployees()
	{
		return employeeRepo.findAll();
	}
	
	
	// Update Employee 
	public Employee updateEmployee(Long empId, Employee updatedEmployee)
	{
		Employee employee = getEmployeeById(empId);
		
		validateAge(updatedEmployee);
		
		employee.setFirstname(updatedEmployee.getFirstname());
		employee.setLastname(updatedEmployee.getLastname());
		employee.setEmail(updatedEmployee.getEmail());
		employee.setPhone(updatedEmployee.getPhone());
		employee.setDepartment(updatedEmployee.getDepartment());
		employee.setDesignation(updatedEmployee.getDesignation());
		employee.setPhone(updatedEmployee.getPhone());
		employee.setDob(updatedEmployee.getDob());
		employee.setQualification(updatedEmployee.getQualification());
		employee.setJoiningDate(updatedEmployee.getJoiningDate());
		employee.setSalary(updatedEmployee.getSalary());
		
		return employeeRepo.save(employee);
	}
	
	
	// Delete Employee By Id
	public void DeleteEmployee(Long empId)
	{
		employeeRepo.deleteById(empId);
	}
	
	
	private void validateAge(Employee employee)
	{
		int age = Period.between(employee.getDob(), LocalDate.now()).getYears();
		
		if (age < 21)
		{
			throw new RuntimeException("Employee Age Must be greater than 21");
		}
	}


}
