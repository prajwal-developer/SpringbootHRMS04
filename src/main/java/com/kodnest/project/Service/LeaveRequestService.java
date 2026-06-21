package com.kodnest.project.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Entity.LeaveRequest;
import com.kodnest.project.Entity.LeaveStatus;
import com.kodnest.project.Repository.EmployeeRepository;
import com.kodnest.project.Repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {
	
	@Autowired
	private LeaveRequestRepository leaveRepo;
	
	
	@Autowired
	private EmployeeRepository empRepo;
	
	

	// Apply Leave
	public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
		
		Employee emp = empRepo.findById(
		        leaveRequest.getEmployee().getEmpId())
		        .orElseThrow(() -> new RuntimeException("Employee not found"));

		leaveRequest.setEmployee(emp);

		return leaveRepo.save(leaveRequest);
	    
	}
	
	 // Get Leave By Id
    public LeaveRequest getLeaveById(Long leaveId) {

        return leaveRepo.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request not found with id : "
                                        + leaveId));
    
    }

	// Get All Leaves
	public List<LeaveRequest> getAllLeaves() {
	    return leaveRepo.findAll();
	}

	// Get Leaves By Employee Id
	public List<LeaveRequest> getLeavesByEmployeeId(Long empId)
	{
	    return leaveRepo.findByEmployeeEmpId(empId);
	}

	// Update Leave
	public LeaveRequest updateLeave(Long leaveId,
	                                LeaveRequest updatedLeave) {

	    LeaveRequest leave = getLeaveById(leaveId);

	    leave.setLeaveType(updatedLeave.getLeaveType());
	    leave.setStartDate(updatedLeave.getStartDate());
	    leave.setEndDate(updatedLeave.getEndDate());
	    leave.setReason(updatedLeave.getReason());
	    leave.setStatus(updatedLeave.getStatus());

	    return leaveRepo.save(leave);
	}

	// Delete Leave
	public void deleteLeave(Long leaveId) {

	    LeaveRequest leave = getLeaveById(leaveId);

	    leaveRepo.delete(leave);
	}
	
	  // Approve Leave
    public LeaveRequest approveLeave(Long leaveId) {

        LeaveRequest leave = getLeaveById(leaveId);

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedBy("Admin");
        leave.setApprovedAt(LocalDateTime.now());

        return leaveRepo.save(leave);
    }
    
    
    // Reject Leave 
    public LeaveRequest rejectLeave(Long leaveId, String rejectionReason) {

           LeaveRequest leave = getLeaveById(leaveId);

            leave.setStatus(LeaveStatus.REJECTED);
            leave.setRejectionReason(rejectionReason);
            leave.setApprovedAt(LocalDateTime.now());

             return leaveRepo.save(leave);
      }
    
 // Mark Leave as Pending
    public LeaveRequest pendingLeave(Long leaveId) {

        LeaveRequest leave = getLeaveById(leaveId);

        leave.setStatus(LeaveStatus.PENDING);
        leave.setApprovedBy(null);
        leave.setApprovedAt(null);
        leave.setRejectionReason(null);

        return leaveRepo.save(leave);
    }

}
