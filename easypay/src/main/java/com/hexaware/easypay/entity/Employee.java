package com.hexaware.easypay.entity;


import java.time.LocalDate;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PastOrPresent;



@Entity
public class Employee {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int employeeId;
	    private String employeeName;
	    private String email;
	    private String position;
	    private double employeeSalary;
	    @PastOrPresent(message = "Payroll date cannot be in the future.")
	    private LocalDate joinDate; 

	    @OneToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "userId")
	    private User user;

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Payroll> payrolls;

		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<LeaveRequest> leaverequest;

		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Attendance> attendance;
		 
		
		 @Enumerated(EnumType.STRING)		 
		 private Role role;
    
		    
		 @ManyToOne
		 @JsonIgnore
		    @JoinColumn(name = "manager_id")
		    private Employee manager;
	    
	    
		 @ManyToOne
	    @JoinColumn(name = "deduction_id", referencedColumnName = "deductionId")
	    private Deductions deduction;

		 @ManyToOne
	    @JoinColumn(name = "benefit_id", referencedColumnName = "benefitId")
	    private Benefits benefit;
		 
		 

		public Employee() {
			super();
			
		}



		public Employee(int employeeId, String employeeName, String email, String position, double employeeSalary,
				LocalDate joinDate, User user, List<Payroll> payrolls, List<LeaveRequest> leaverequest,
				List<Attendance> attendance, Role role, Employee manager, Deductions deduction, Benefits benefit) {
			super();
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.email = email;
			this.position = position;
			this.employeeSalary = employeeSalary;
			this.joinDate = joinDate;
			this.user = user;
			this.payrolls = payrolls;
			this.leaverequest = leaverequest;
			this.attendance = attendance;
			this.role = role;
			this.manager = manager;
			this.deduction = deduction;
			this.benefit = benefit;
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



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPosition() {
			return position;
		}



		public void setPosition(String position) {
			this.position = position;
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



		public List<LeaveRequest> getLeaverequest() {
			return leaverequest;
		}



		public void setLeaverequest(List<LeaveRequest> leaverequest) {
			this.leaverequest = leaverequest;
		}



		public List<Attendance> getAttendance() {
			return attendance;
		}



		public void setAttendance(List<Attendance> attendance) {
			this.attendance = attendance;
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



		public Deductions getDeduction() {
			return deduction;
		}



		public void setDeduction(Deductions deduction) {
			this.deduction = deduction;
		}



		public Benefits getBenefit() {
			return benefit;
		}



		public void setBenefit(Benefits benefit) {
			this.benefit = benefit;
		}



		@Override
		public String toString() {
			return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email
					+ ", position=" + position + ", employeeSalary=" + employeeSalary + ", joinDate=" + joinDate
					+ ", user=" + user + ", payrolls=" + payrolls + ", leaverequest=" + leaverequest + ", attendance="
					+ attendance + ", role=" + role + ", manager=" + manager + ", deduction=" + deduction + ", benefit="
					+ benefit + "]";
		}



		



	  


	    
	}