package com.hexaware.easypay.dto;

public class ManagerDTO {
	
		 private int managerId ;
		 private String employeeName;
		 
		 
		public ManagerDTO() {
			super();
		}
		
		
		public ManagerDTO(int managerId, String employeeName, String position) {
			super();
			this.managerId = managerId;
			this.employeeName = employeeName;
			
		
		}


		public int getManagerId() {
			return managerId;
		}
		public void setManagerId(int managerId) {
			this.managerId = managerId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}


		@Override
		public String toString() {
			return "ManagerDTO [managerId=" + managerId + ", employeeName=" + employeeName + "]";
		}
		
		

		 
		 

	}
