package com.kodnest.project.Entity;


import com.kodnest.project.Entity.LeaveStatus;
import com.kodnest.project.Entity.LeaveType;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leave_requests")
@Data
public class LeaveRequest {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LeaveType leaveType;

	@FutureOrPresent
	@Column(nullable = false)
	private LocalDate startDate;

	@FutureOrPresent
	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private Integer totalDays;

	@NotBlank(message = "Reason is required")
	@Column(length = 500)
	private String reason;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LeaveStatus status = LeaveStatus.PENDING;


	private String approvedBy;

	private LocalDateTime approvedAt;

	private String rejectionReason;

	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {

	    this.createdAt = LocalDateTime.now();

	    if (startDate != null && endDate != null) {

	        if (endDate.isBefore(startDate)) {
	            throw new RuntimeException(
	                "End date cannot be before start date"
	            );
	        }

	        this.totalDays =
	                (int) ChronoUnit.DAYS.between(
	                        startDate,
	                        endDate
	                ) + 1;
	    }
	}

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveRequest(Long leaveId, Employee employee, LeaveType leaveType, @FutureOrPresent LocalDate startDate,
			@FutureOrPresent LocalDate endDate, Integer totalDays,
			@NotBlank(message = "Reason is required") String reason, LeaveStatus status, String approvedBy,
			LocalDateTime approvedAt, String rejectionReason, LocalDateTime createdAt) {
		super();
		this.leaveId = leaveId;
		this.employee = employee;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalDays = totalDays;
		this.reason = reason;
		this.status = status;
		this.approvedBy = approvedBy;
		this.approvedAt = approvedAt;
		this.rejectionReason = rejectionReason;
		this.createdAt = createdAt;
	}

	public LeaveRequest(Employee employee, LeaveType leaveType, @FutureOrPresent LocalDate startDate,
			@FutureOrPresent LocalDate endDate, Integer totalDays,
			@NotBlank(message = "Reason is required") String reason, LeaveStatus status, String approvedBy,
			LocalDateTime approvedAt, String rejectionReason, LocalDateTime createdAt) {
		super();
		this.employee = employee;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalDays = totalDays;
		this.reason = reason;
		this.status = status;
		this.approvedBy = approvedBy;
		this.approvedAt = approvedAt;
		this.rejectionReason = rejectionReason;
		this.createdAt = createdAt;
	}

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
