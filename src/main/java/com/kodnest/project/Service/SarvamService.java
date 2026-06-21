package com.kodnest.project.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.project.Entity.Attendance;
import com.kodnest.project.Entity.Employee;
import com.kodnest.project.Entity.LeaveRequest;
import com.kodnest.project.Entity.Payroll;
import com.kodnest.project.Repository.AttendanceRepository;
import com.kodnest.project.Repository.EmployeeRepository;
import com.kodnest.project.Repository.LeaveRequestRepository;
import com.kodnest.project.Repository.PayrollRepository;

@Service
public class SarvamService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LeaveRequestRepository leaveRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    public String chat(String message) {

        String msg = message.toLowerCase();

        // ================= GENERAL =================

        if (msg.contains("total employees")
                || msg.contains("employee count")) {

            return "Total Employees : "
                    + employeeRepository.count();
        }

        if (msg.contains("all employees")
                || msg.contains("list employees")) {

            List<Employee> employees =
                    employeeRepository.findAll();

            StringBuilder sb =
                    new StringBuilder("Employee List\n\n");

            for (Employee emp : employees) {

                sb.append("ID : ")
                  .append(emp.getEmpId())
                  .append(" | ")
                  .append(emp.getFirstname())
                  .append(" ")
                  .append(emp.getLastname())
                  .append("\n");
            }

            return sb.toString();
        }

        // ================= SEARCH EMPLOYEE BY NAME =================

        List<Employee> allEmployees =
                employeeRepository.findAll();

        for (Employee emp : allEmployees) {

            String fullName =
                    (emp.getFirstname() + " "
                    + emp.getLastname()).toLowerCase();

            if (msg.contains(fullName)) {

                return """
                        Employee Found

                        ID : %d
                        Name : %s %s
                        Email : %s
                        Department : %s
                        Designation : %s
                        """
                        .formatted(
                                emp.getEmpId(),
                                emp.getFirstname(),
                                emp.getLastname(),
                                emp.getEmail(),
                                emp.getDepartment(),
                                emp.getDesignation());
            }
        }

        // ================= EMPLOYEE ID EXTRACTION =================

        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(msg);

        if (!matcher.find()) {
            return """
                    Please provide Employee ID.

                    Example:
                    - What is salary of employee 1?
                    - What is email of employee 2?
                    - Show attendance of employee 3
                    """;
        }

        Long empId =
                Long.parseLong(matcher.group(1));

        Employee emp =
                employeeRepository.findById(empId)
                        .orElse(null);

        if (emp == null) {
            return "Employee not found with ID "
                    + empId;
        }

        // ================= EMPLOYEE =================

        if (msg.contains("name")) {
            return "Employee Name : "
                    + emp.getFirstname()
                    + " "
                    + emp.getLastname();
        }

        if (msg.contains("email")) {
            return "Email : "
                    + emp.getEmail();
        }

        if (msg.contains("phone")) {
            return "Phone : "
                    + emp.getPhone();
        }

        if (msg.contains("department")) {
            return "Department : "
                    + emp.getDepartment();
        }

        if (msg.contains("designation")) {
            return "Designation : "
                    + emp.getDesignation();
        }

        if (msg.contains("qualification")) {
            return "Qualification : "
                    + emp.getQualification();
        }

        if (msg.contains("salary")
                && !msg.contains("net salary")) {

            return "Salary : ₹"
                    + emp.getSalary();
        }

        if (msg.contains("joining")
                || msg.contains("join date")
                || msg.contains("joined")) {

            return "Joining Date : "
                    + emp.getJoiningDate();
        }

        if (msg.contains("dob")
                || msg.contains("birth")) {

            return "DOB : "
                    + emp.getDob();
        }

        if (msg.contains("age")) {

            int age =
                    Period.between(
                            emp.getDob(),
                            LocalDate.now())
                            .getYears();

            return "Age : "
                    + age + " Years";
        }

        // ================= ATTENDANCE =================

        if (msg.contains("attendance")
                || msg.contains("check in")
                || msg.contains("check out")
                || msg.contains("working hours")
                || msg.contains("status")) {

            Attendance att =
                    attendanceRepository
                    .findTopByEmployeeEmpIdOrderByAttendanceIdDesc(empId)
                    .orElse(null);

            if (att == null) {
                return "No attendance found.";
            }

            return """
                    Attendance Details

                    Date : %s
                    Status : %s
                    Check In : %s
                    Check Out : %s
                    Working Hours : %s
                    """
                    .formatted(
                            att.getAttendanceDate(),
                            att.getStatus(),
                            att.getCheckIn(),
                            att.getCheckOut(),
                            att.getWorkingHours());
        }

        // ================= LEAVE =================

        if (msg.contains("leave")) {

            LeaveRequest leave =
                    leaveRepository
                    .findTopByEmployeeEmpIdOrderByLeaveIdDesc(empId)
                    .orElse(null);

            if (leave == null) {
                return "No leave record found.";
            }

            return """
                    Leave Details

                    Leave Type : %s
                    Start Date : %s
                    End Date : %s
                    Total Days : %d
                    Status : %s
                    """
                    .formatted(
                            leave.getLeaveType(),
                            leave.getStartDate(),
                            leave.getEndDate(),
                            leave.getTotalDays(),
                            leave.getStatus());
        }

        // ================= PAYROLL =================

        if (msg.contains("payroll")
                || msg.contains("net salary")
                || msg.contains("basic salary")
                || msg.contains("hra")
                || msg.contains("allowance")
                || msg.contains("bonus")
                || msg.contains("deduction")
                || msg.contains("payment")) {

            Payroll payroll =
                    payrollRepository
                    .findTopByEmployeeEmpIdOrderByPayrollIdDesc(empId)
                    .orElse(null);

            if (payroll == null) {
                return "No payroll found.";
            }

            if (msg.contains("net salary")) {
                return "Net Salary : ₹"
                        + payroll.getNetSalary();
            }

            if (msg.contains("basic salary")) {
                return "Basic Salary : ₹"
                        + payroll.getBasicSalary();
            }

            if (msg.contains("hra")) {
                return "HRA : ₹"
                        + payroll.getHra();
            }

            if (msg.contains("allowance")) {
                return "Allowances : ₹"
                        + payroll.getAllowances();
            }

            if (msg.contains("bonus")) {
                return "Bonus : ₹"
                        + payroll.getBonus();
            }

            if (msg.contains("deduction")) {
                return "Deductions : ₹"
                        + payroll.getDeductions();
            }

            if (msg.contains("payment")) {
                return "Payment Date : "
                        + payroll.getPaymentDate();
            }

            return """
                    Payroll Details

                    Month : %d
                    Year : %d
                    Basic Salary : ₹%.2f
                    HRA : ₹%.2f
                    Allowances : ₹%.2f
                    Bonus : ₹%.2f
                    Deductions : ₹%.2f
                    Net Salary : ₹%.2f
                    Payment Date : %s
                    """
                    .formatted(
                            payroll.getPayMonth(),
                            payroll.getPayYear(),
                            payroll.getBasicSalary(),
                            payroll.getHra(),
                            payroll.getAllowances(),
                            payroll.getBonus(),
                            payroll.getDeductions(),
                            payroll.getNetSalary(),
                            payroll.getPaymentDate());
        }

        // ================= FULL EMPLOYEE DETAILS =================

        return """
                Employee Details

                ID : %d
                Name : %s %s
                Email : %s
                Phone : %s
                Department : %s
                Designation : %s
                Qualification : %s
                Salary : ₹%.2f
                DOB : %s
                Joining Date : %s
                """
                .formatted(
                        emp.getEmpId(),
                        emp.getFirstname(),
                        emp.getLastname(),
                        emp.getEmail(),
                        emp.getPhone(),
                        emp.getDepartment(),
                        emp.getDesignation(),
                        emp.getQualification(),
                        emp.getSalary(),
                        emp.getDob(),
                        emp.getJoiningDate());
    }
}