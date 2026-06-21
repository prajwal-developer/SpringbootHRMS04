package com.kodnest.project.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll")
@Data
public class Payroll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payrollId;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
//	@ManyToOne
//	@JoinColumn(name = "employee_id") 
//	private Employee employee; 
	
	@Column(nullable = false) 
	private Integer payMonth;
	
	@Column(nullable = false) 
	private Integer payYear; 
	
	@Positive 
	@Column(nullable = false)
	private Double basicSalary;
	
	private Double hra;
	
	private Double allowances;
	
	private Double bonus;
	
	private Double deductions; 
	@Column(nullable = false)
	private Double netSalary;
	
	private LocalDate paymentDate;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist()
	{ 
		this.createdAt = LocalDateTime.now();
		double totalEarnings = basicSalary + (hra == null ? 0 : hra) + (allowances == null ? 0 : allowances) + (bonus == null ? 0 : bonus);
		double totalDeductions = (deductions == null ? 0 : deductions); this.netSalary = totalEarnings - totalDeductions;
		this.paymentDate = LocalDate.now();
		
		
		
	}

	public Payroll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payroll(Long payrollId, Employee employee, Integer payMonth, Integer payYear, @Positive Double basicSalary,
			Double hra, Double allowances, Double bonus, Double deductions, Double netSalary, LocalDate paymentDate,
			LocalDateTime createdAt) {
		super();
		this.payrollId = payrollId;
		this.employee = employee;
		this.payMonth = payMonth;
		this.payYear = payYear;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.allowances = allowances;
		this.bonus = bonus;
		this.deductions = deductions;
		this.netSalary = netSalary;
		this.paymentDate = paymentDate;
		this.createdAt = createdAt;
	}

	public Payroll(Employee employee, Integer payMonth, Integer payYear, @Positive Double basicSalary, Double hra,
			Double allowances, Double bonus, Double deductions, Double netSalary, LocalDate paymentDate,
			LocalDateTime createdAt) {
		super();
		this.employee = employee;
		this.payMonth = payMonth;
		this.payYear = payYear;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.allowances = allowances;
		this.bonus = bonus;
		this.deductions = deductions;
		this.netSalary = netSalary;
		this.paymentDate = paymentDate;
		this.createdAt = createdAt;
	}

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(Integer payMonth) {
		this.payMonth = payMonth;
	}

	public Integer getPayYear() {
		return payYear;
	}

	public void setPayYear(Integer payYear) {
		this.payYear = payYear;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getHra() {
		return hra;
	}

	public void setHra(Double hra) {
		this.hra = hra;
	}

	public Double getAllowances() {
		return allowances;
	}

	public void setAllowances(Double allowances) {
		this.allowances = allowances;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getDeductions() {
		return deductions;
	}

	public void setDeductions(Double deductions) {
		this.deductions = deductions;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
