import { Component } from '@angular/core';
import { AttendanceDTO } from 'src/app/model/AttendanceDTO';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-submitattendance',
  templateUrl: './submitattendance.component.html',
  styleUrls: ['./submitattendance.component.css']
})
export class SubmitattendanceComponent {
   attendance:AttendanceDTO={
    employeeId: 0,
    workDate: new Date() ,
    hoursWorked:0,
    status:''
  }

  constructor(private service :EmployeeService){

  }


  submitAttendance(attendance:AttendanceDTO){

    this.service.submitAttendance(attendance).subscribe(
      (response)=>{
        console.log(response);
        alert("Attendance Submission Succesfull");
      },
      (err)=>{

        console.log(err);
        alert("Attendance Submission succesfull !");
      }
    );

  }

}
