import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LeaveRequest } from 'src/app/model/LeaveRequest';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-requestleave',
  templateUrl: './requestleave.component.html',
  styleUrls: ['./requestleave.component.css']
})
export class RequestleaveComponent {
    leavesdata: LeaveRequest = {
    employeeId: 0,
    startDate: new Date(),
    endDate: new Date(),
    leaveType: '',
    managerId: 0,
    leaveStatus: 'PENDING'
  };

  constructor(private leaveService: EmployeeService) {}

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.leaveService.requestLeave(this.leavesdata).subscribe({
        next: (response) => {
          alert('Leave requested successfully!');
          form.resetForm();
        },
        error: (error) => {
          alert('Error requesting leave: ' + error.message);
        }
      });
    }
  }
}