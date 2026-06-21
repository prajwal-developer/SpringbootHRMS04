package com.kodnest.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.project.Entity.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{
	
	List<LeaveRequest> findByEmployeeEmpId(Long employeeId);
	
	  Optional<LeaveRequest> findTopByEmployeeEmpIdOrderByLeaveIdDesc(Long empId);
	  
	  

}
