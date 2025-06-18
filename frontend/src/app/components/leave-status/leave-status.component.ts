import { Component } from '@angular/core';
import { LeaveRequestDTO } from 'src/app/model/LeaveRequestDTO';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-leave-status',
  templateUrl: './leave-status.component.html',
  styleUrls: ['./leave-status.component.css']
})
export class LeaveStatusComponent {

  employeeId: number = 0;
  leaveRequests: LeaveRequestDTO[] = [];
  message: string = '';

  constructor(private leaveService: EmployeeService) {}

  fetchLeaveStatus(): void {
    if (!this.employeeId) {
      this.message = 'Please enter a valid employee ID.';
      return;
    }

    this.leaveService.getLeaveStatusByEmployeeId(this.employeeId).subscribe({
      next: (data) => {
        this.leaveRequests = data;
        this.message = data.length ? '' : 'No leave records found.';
      },
      error: (err) => {
        console.error(err);
        this.message = 'Error fetching leave status.';
        this.leaveRequests = [];
      }
    });
  }
}

