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
	    private int empId;
	    private String empName;
	    private String position;
	    private String empDepartment;
	    private double empsalary;
	    private LocalDate joinDate; 

	    @OneToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "userId")
	    private User user;

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Payroll> payrolls;

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<ComplianceReport> complianceReports;
	    
		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Leaves> leaves;

		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
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
	    
	    





		public Employee(int empId, String empName, String position, String empDepartment, double empsalary, LocalDate joinDate,
	            User user, Deductions deductions, Benefits benefits) {
	this.empId = empId;
	this.empName = empName;
	this.position = position;
	this.empDepartment = empDepartment;
	this.empsalary = empsalary;
	this.joinDate = joinDate;
	this.user = user;
	this.deductions = deductions;
	this.benefits = benefits;
	}

	   

	    public int getEmpId() {
	        return empId;
	    }

	    public void setEmpId(int empId) {
	        this.empId = empId;
	    }

	    public String getEmpName() {
	        return empName;
	    }

	    public void setEmpName(String empName) {
	        this.empName = empName;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public String getEmpDepartment() {
	        return empDepartment;
	    }

	    public void setEmpDepartment(String empDepartment) {
	        this.empDepartment = empDepartment;
	    }

	    public double getEmpsalary() {
	        return empsalary;
	    }

	    public void setEmpsalary(double empsalary) {
	        this.empsalary = empsalary;
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

	    public Employee getManager() {
	        return manager;
	    }

	    public void setManager(Employee manager) {
	        this.manager = manager;
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



	    
	}