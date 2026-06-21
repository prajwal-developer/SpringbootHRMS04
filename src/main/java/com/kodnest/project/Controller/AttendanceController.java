package com.kodnest.project.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodnest.project.Entity.Attendance;
import com.kodnest.project.Service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
	
	
	@Autowired 
	private AttendanceService attendanceService; 
	// Create Attendance 
	@PostMapping
	public Attendance createAttendance(@RequestBody Attendance attendance) 
	{
		return attendanceService.addAttendance(attendance);
	} 
	
	// Get Attendance By Id @GetMapping("/{attendanceId}") 
	
	// Get Attendance By Id
	@GetMapping("/{attendanceId}")
	public Attendance getAttendanceById(
	        @PathVariable Long attendanceId) {

	    return attendanceService.getAttendanceById(attendanceId);
	}
	
	
	// Get Attendance By Employee Id
	@GetMapping("/employee/{empId}")
	public Attendance getAttendanceByEmployeeId( @PathVariable Long empId)
	{
		return attendanceService.getAttendanceByEmployeeId(empId);
	} 
	// Get All Attendance
	@GetMapping 
	public List<Attendance> getAllAttendance()
	{
		return attendanceService.getAttendances();
		
	}
	// Update Attendance
	@PutMapping("/{attendanceId}") 
	public Attendance updateAttendance( @PathVariable Long attendanceId, @RequestBody Attendance attendance) 
	{
		return attendanceService.updateAttendance( attendanceId, attendance );
		
	} 
	
	// Delete Attendance
	@DeleteMapping("/{attendanceId}")
	public String deleteAttendance( @PathVariable Long attendanceId)
	{
		attendanceService.deleteAttendance(attendanceId); 
		return "Attendance deleted successfully";
		
	}
	
	

}
