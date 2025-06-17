import { Component } from '@angular/core';
import { LeaveDto } from 'src/app/model/LeaveDto';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-requestleave',
  templateUrl: './requestleave.component.html',
  styleUrls: ['./requestleave.component.css']
})
export class RequestleaveComponent {
    leavesdata: LeaveDto={
    employeeId:0,
    startDate: new Date(),
    endDate:new Date(),
    leaveType: '',
     managerID:{
        managerId :0
    }
    

  }

  constructor(private employeeService: EmployeeService ) {
  
  }

  onSubmit(): void {
    

    console.log("Request Payload: " , this.leavesdata);
      this.employeeService['LeaveDto'](this.leavesdata).subscribe({
        next: (response: any) => {
          alert("Leave Request Submission Succesfull");
          console.log(response);
        },
        error: (error: any) => {
          alert("Leave Request Submission Unsuccesfull");
          console.log(error);
        }
      });
    }

}
