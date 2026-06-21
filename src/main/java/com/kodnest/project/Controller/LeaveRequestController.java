package com.kodnest.project.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kodnest.project.Entity.LeaveRequest;
import com.kodnest.project.Service.LeaveRequestService;

@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = "*")
public class LeaveRequestController {
	
	@Autowired 
	private LeaveRequestService leaveService; 
	
	// Apply Leave 
	@PostMapping 
	public LeaveRequest applyLeave( @RequestBody LeaveRequest leaveRequest)
	{
		return leaveService.applyLeave(leaveRequest); 
		
	} 
	
	// Get Leave By Id 
	@GetMapping("/{leaveId}")
	public LeaveRequest getLeaveById( @PathVariable Long leaveId) 
	{
		return leaveService.getLeaveById(leaveId); 
		
	} 
	
	// Get All Leaves
	@GetMapping
	public List<LeaveRequest> getAllLeaves() 
	{ 
		return leaveService.getAllLeaves(); 
		
	}
	
	@DeleteMapping("/{leaveId}")
	public String deleteLeave(
	@PathVariable Long leaveId)
	{
	    leaveService.deleteLeave(leaveId);
	    return "Leave Deleted Successfully";
	}
	  @PutMapping("/{id}/approve")
	    public LeaveRequest approveLeave(@PathVariable Long id) {
	        return leaveService.approveLeave(id);
	    }
	
	
	  @PutMapping("/{id}/reject")
	    public LeaveRequest rejectLeave(
	            @PathVariable Long id,
	            @RequestParam String reason) {

	        return leaveService.rejectLeave(id, reason);
	    }
	
	
	@PutMapping("/{id}/pending")
	public LeaveRequest pendingLeave(@PathVariable Long id) {
	    return leaveService.pendingLeave(id);
	}
	

}
