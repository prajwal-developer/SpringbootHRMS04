package com.kodnest.project.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "attendance",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"employee_id", "attendance_date"}
        )
    }
)
@Data
public class Attendance {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate attendanceDate;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private Double workingHours;

    @Column(nullable = false)
    private String status; // PRESENT, ABSENT, HALF_DAY, LEAVE

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (checkIn != null && checkOut != null) {
            workingHours = (double)
                    java.time.Duration.between(checkIn, checkOut)
                    .toHours();
        }
    }

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Long attendanceId, Employee employee, LocalDate attendanceDate, LocalTime checkIn,
			LocalTime checkOut, Double workingHours, String status, LocalDateTime createdAt) {
		super();
		this.attendanceId = attendanceId;
		this.employee = employee;
		this.attendanceDate = attendanceDate;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.workingHours = workingHours;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Attendance(Employee employee, LocalDate attendanceDate, LocalTime checkIn, LocalTime checkOut,
			Double workingHours, String status, LocalDateTime createdAt) {
		super();
		this.employee = employee;
		this.attendanceDate = attendanceDate;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.workingHours = workingHours;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public LocalTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalTime checkOut) {
		this.checkOut = checkOut;
	}

	public Double getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Double workingHours) {
		this.workingHours = workingHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", employee=" + employee + ", attendanceDate="
				+ attendanceDate + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", workingHours=" + workingHours
				+ ", status=" + status + ", createdAt=" + createdAt + "]";
	}

    
	
}