import { Component, OnInit } from '@angular/core';
import { LeaveRequest } from 'src/app/model/LeaveRequest';
import { LeavesDTO } from 'src/app/model/LeavesDTO';
import { ManagerService } from 'src/app/service/manager.service';

@Component({
  selector: 'app-updateleaverequest',
  templateUrl: './updateleaverequest.component.html',
  styleUrls: ['./updateleaverequest.component.css']
})
export class UpdateleaverequestComponent  {
  leaveId: number = 0;
  status: string = '';
  managerId: number = 1; // Set this dynamically from logged-in user if available
  message: string = '';

  constructor(private leaveService: ManagerService) {}

  submitLeaveDecision(): void {
    if (!this.leaveId || !this.status) {
      this.message = 'Please provide both Leave ID and Status.';
      return;
    }

    this.leaveService.updateLeaveStatus(this.managerId, this.leaveId, this.status)
      .subscribe({
        next: (response: LeavesDTO) => {
          this.message = `Leave request ${this.status.toLowerCase()} successfully.`;
        },
        error: (err) => {
          console.error('Error updating leave:', err);
          this.message = 'An error occurred while processing the request.';
        }
      });
  }
}

