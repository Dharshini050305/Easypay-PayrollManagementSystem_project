import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from 'src/app/model/Employee';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employee: Employee = {
    employeeId: 0,
    employeeName: '',
    position: '',
    employeeSalary: 0,
    joinDate: '',
    userId: 0,
    deductionId: 0,
    benefitId: 0,
    managerId: undefined,
    email: ''
  };

  constructor(
    private service: AdminService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const employeeId = +this.route.snapshot.paramMap.get('employeeId')!;
    this.service.getEmployeeById(employeeId).subscribe((data) => {
      this.employee = data;
    });
  }

  updateEmployee(): void {
  if (this.employee && this.employee.employeeId) {
    this.service.updateEmployee(this.employee.employeeId, this.employee).subscribe(
      () => {
        console.log('Employee updated successfully');
        alert('Employee updated successfully!');
      },
      (error) => {
        console.error('Error updating employee:', error);
        alert('Failed to update employee. Please try again.');
      }
    );
  } else {
    alert('Employee data is incomplete or invalid.');
  }
}

}
