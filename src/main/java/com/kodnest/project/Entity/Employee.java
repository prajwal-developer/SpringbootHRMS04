package com.kodnest.project.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @NotBlank(message = "First name is required")
    private String firstname;
    
    @NotBlank(message = "Last name is required")
    private String lastname;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is Required")
    @Column(nullable = false , unique = true)
    private String email;
    
    @Pattern(
    		regexp = "^[6-9]\\d{9}$",
    		message = "Invalid Phone number"
    		
    		)
    private String phone;
    
    @NotBlank
    private String department;
    
    @NotBlank
    private String designation;
    
    @Past(message = "DOB must in the past")
    @Column(nullable = false)
    private LocalDate dob;
    
    @Column(nullable = false)
    private LocalDate joiningDate;
    
    @Positive(message = "salary must be greater than 0")
    @Column(nullable = false)
    private Double salary;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Qualification qualification;
    
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(
	        String firstname,
	        String lastname,
	        String email,
	        String phone,
	        String department,
	        String designation,
	        LocalDate dob,
	        LocalDate joiningDate,
	        Double salary,
	        Qualification qualification) {

	    this.firstname = firstname;
	    this.lastname = lastname;
	    this.email = email;
	    this.phone = phone;
	    this.department = department;
	    this.designation = designation;
	    this.dob = dob;
	    this.joiningDate = joiningDate;
	    this.salary = salary;
	    this.qualification = qualification;
	}

	public Long getEmpId() {
	    return empId;
	}

	public void setEmpId(Long empId) {
	    this.empId = empId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
    
	
    
}