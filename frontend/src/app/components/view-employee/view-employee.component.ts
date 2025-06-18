import { Component } from '@angular/core';
import { Employee } from 'src/app/model/Employee';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent {
  constructor(private  service:AdminService){
  
    }
  
    employeeList: Employee[] = [];
    selectedEmployee: Employee | null = null;
  getEmployeeById(employeeId: number) {
    this.service.getEmployeeById(employeeId).subscribe(
      (employee) => {
        this.selectedEmployee = employee;
        console.log(this.selectedEmployee);
      },
      (err) => {
        console.error('Error fetching employee by ID:', err);
        
      if (err.status === 404) {
        alert(`Employee with ID ${employeeId} not found.`);
      } else {
        alert('An unexpected error occurred. Please try again later.');
      }
      }
    );
  }

}
