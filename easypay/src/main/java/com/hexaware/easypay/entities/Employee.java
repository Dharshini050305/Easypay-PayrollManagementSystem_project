package com.hexaware.easypay.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
    private String employeeName;
    private String position;
    private String employeeDepartment;
    private double employeeSalary;
    private LocalDate joinDate;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ComplianceReport> complianceReports;
    
	 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	    private List<Leaves> leaves;

	 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	    private List<Attendance> attendances;
	 
	
	    @ManyToOne
	    @JoinColumn(name = "role_id", referencedColumnName = "roleID")
	    private Role role; 

	    
	    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    
    
    @ManyToOne
    @JoinColumn(name = "deduction_id", referencedColumnName = "deductionId")
    private Deductions deductions;

    @ManyToOne
    @JoinColumn(name = "benefit_id", referencedColumnName = "benifitId")
    private Benefits benefits;
    
	public Employee() {
		super();
		
	}

	public Employee(int employeeId, String employeeName, String position, String employeeDepartment,
			double employeeSalary, LocalDate joinDate, User user, List<Payroll> payrolls,
			List<ComplianceReport> complianceReports, List<Leaves> leaves, List<Attendance> attendances, Role role,
			Employee manager, Deductions deductions, Benefits benefits) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.position = position;
		this.employeeDepartment = employeeDepartment;
		this.employeeSalary = employeeSalary;
		this.joinDate = joinDate;
		this.user = user;
		this.payrolls = payrolls;
		this.complianceReports = complianceReports;
		this.leaves = leaves;
		this.attendances = attendances;
		this.role = role;
		this.manager = manager;
		this.deductions = deductions;
		this.benefits = benefits;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payroll> getPayrolls() {
		return payrolls;
	}

	public void setPayrolls(List<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	public List<ComplianceReport> getComplianceReports() {
		return complianceReports;
	}

	public void setComplianceReports(List<ComplianceReport> complianceReports) {
		this.complianceReports = complianceReports;
	}

	public List<Leaves> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leaves> leaves) {
		this.leaves = leaves;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Deductions getDeductions() {
		return deductions;
	}

	public void setDeductions(Deductions deductions) {
		this.deductions = deductions;
	}

	public Benefits getBenefits() {
		return benefits;
	}

	public void setBenefits(Benefits benefits) {
		this.benefits = benefits;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", position=" + position
				+ ", employeeDepartment=" + employeeDepartment + ", employeeSalary=" + employeeSalary + ", joinDate="
				+ joinDate + ", user=" + user + ", payrolls=" + payrolls + ", complianceReports=" + complianceReports
				+ ", leaves=" + leaves + ", attendances=" + attendances + ", role=" + role + ", manager=" + manager
				+ ", deductions=" + deductions + ", benefits=" + benefits + "]";
	}

	
	
}
