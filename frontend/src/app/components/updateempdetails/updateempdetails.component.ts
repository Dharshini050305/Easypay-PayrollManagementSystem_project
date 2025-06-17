import { Component } from '@angular/core';
import { EmpuDto } from 'src/app/model/EmpuDto';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-updateempdetails',
  templateUrl: './updateempdetails.component.html',
  styleUrls: ['./updateempdetails.component.css']
})
export class UpdateempdetailsComponent {
   empDto:EmpuDto={
    employeeId:0,
    employeeName:'',
    position:'',
    email:'',

  };


  constructor(private service:EmployeeService){

  }

  updateEmpPersonalInformation(empuDto:EmpuDto){

    this.service.updateEmpPersonalInformation(empuDto).subscribe(

      (response)=>{
        console.log(response);
        alert("Employee Details Updated Succesfully! ");
      },
      (err)=>{
        console.log(err);
        alert("Employee Update Unsuccesful")
      }
    );

  }

}
