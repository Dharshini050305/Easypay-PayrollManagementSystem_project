import { Component } from '@angular/core';
import { LeaveRequestDTO } from 'src/app/model/LeaveRequestDTO';
import { ManagerService } from 'src/app/service/manager.service';

@Component({
  selector: 'app-manager-leaves',
  templateUrl: './manager-leaves.component.html',
  styleUrls: ['./manager-leaves.component.css']
})
export class ManagerLeavesComponent {
  managerId: number = 0;
  leaveList: LeaveRequestDTO[] = [];
  message: string = '';

  constructor(private leaveService: ManagerService) {}

  getLeaves() {
    if (!this.managerId) {
      this.message = 'Enter a valid Manager ID.';
      return;
    }

    this.leaveService.getLeavesByManagerId(this.managerId).subscribe({
      next: (data) => {
        this.leaveList = data;
        this.message = data.length ? '' : 'No leaves found for this manager.';
      },
      error: (err) => {
        console.error(err);
        this.message = 'Error fetching leave data.';
        this.leaveList = [];
      }
    });
  }
}


