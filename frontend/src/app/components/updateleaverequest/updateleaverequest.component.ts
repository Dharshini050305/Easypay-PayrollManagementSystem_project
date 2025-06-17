import { Component, OnInit } from '@angular/core';
import { LeaveRequest } from 'src/app/model/LeaveRequest';
import { LeavesDTO } from 'src/app/model/LeavesDTO';
import { ManagerService } from 'src/app/service/manager.service';

@Component({
  selector: 'app-updateleaverequest',
  templateUrl: './updateleaverequest.component.html',
  styleUrls: ['./updateleaverequest.component.css']
})
export class UpdateleaverequestComponent implements OnInit {
   leaves: LeavesDTO[] = [];
  managerId: number | null = null;
  showCard = false;
  selectedLeave: LeavesDTO | null = null;
  statusOptions = ['Approved', 'Rejected'];
  errorMessage: string | null = null;

  constructor(private leaveService: ManagerService) {}

  ngOnInit(): void {}

  fetchLeaves(): void {
    if (this.managerId !== null) {
      this.leaveService.getAllLeavesByManagerId(this.managerId).subscribe(
        (data: LeavesDTO[]) => {
          this.leaves = data;
          this.errorMessage = null;
        },
        (error) => {
          this.errorMessage = 'Error fetching leaves. Please check the Manager ID.';
          console.error(error);
        }
      );
    }
  }

  openStatusCard(leave: LeavesDTO): void {
    this.selectedLeave = leave;
    this.showCard = true;
  }

  updateStatus(status: string): void {
    if (this.selectedLeave && this.managerId !== null) {
      this.leaveService.updateLeaveStatus(this.managerId, this.selectedLeave.leaveId, status)
        .subscribe(
          (updatedLeave: LeavesDTO) => {
            this.selectedLeave!.leaveStatus = updatedLeave.leaveStatus;
            this.showCard = false;
            alert('Leave Status Update Successful');
          },
          (error) => {
            console.error('Error updating leave status', error);
            alert('Leave Status Update Failed');
          }
        );
    }
  }


}

