package com.kodnest.project.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.project.Entity.Attendance;
import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Repository.AttendanceRepository;
import com.kodnest.project.Repository.EmployeeRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;

	
	public Attendance addAttendance(Attendance attendance) {

	    Long empId = attendance.getEmployee().getEmpId();

	    Employee employee = employeeRepo.findById(empId)
	            .orElseThrow(() -> new RuntimeException("Employee Not Found"));

	    attendance.setEmployee(employee);

	    return attendanceRepo.save(attendance);
	}
    
	
	
	public Attendance getAttendanceByEmployeeId(Long empId)
	{
	    return attendanceRepo.findByEmployeeEmpId(empId)
	        .orElseThrow(() ->
	            new RuntimeException(
	                "Attendance Not found with EmployeeId : "
	                + empId));
	}
	
	public Attendance getAttendanceById(Long attendanceId)
	{
		 return attendanceRepo.findById(attendanceId).orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
	}
	
	public List<Attendance> getAttendances()
	{
	   return attendanceRepo.findAll();
	}
	
	public Attendance getAttendanceByDate(LocalDate attendanceDate) {
	    return attendanceRepo.findByAttendanceDate(attendanceDate)
	            .orElseThrow(() ->
	                new RuntimeException("Attendance Not Found with this date: " + attendanceDate));
	}
	
	public Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance)
	{
		Attendance attendance = getAttendanceById(attendanceId);
		
		attendance.setAttendanceDate(updatedAttendance.getAttendanceDate());
		attendance.setCheckIn(updatedAttendance.getCheckIn()); attendance.setCheckOut(updatedAttendance.getCheckOut()); 
		attendance.setWorkingHours(updatedAttendance.getWorkingHours()); 
		attendance.setStatus(updatedAttendance.getStatus());
		
		return attendanceRepo.save(attendance);
		
	}
	
	public void deleteAttendance(Long attendanceId)
	{
		attendanceRepo.deleteById(attendanceId);
	}
	
}
