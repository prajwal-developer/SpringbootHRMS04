package com.kodnest.project.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.project.Entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	

    Optional<Attendance> findByAttendanceId(Long attendanceId);

    Optional<Attendance> findByEmployeeEmpId(Long empId);

    Optional<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    Optional<Attendance> findTopByEmployeeEmpIdOrderByAttendanceIdDesc(Long empId);
    
    
    
    boolean existsByAttendanceId(Long attendanceId);

    boolean existsByEmployeeEmpId(Long empId);

    boolean existsByAttendanceDate(LocalDate attendanceDate);
    
   

}
