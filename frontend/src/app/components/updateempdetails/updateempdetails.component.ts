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

  updateEmpPersonalInformation(empuDto: EmpuDto): void {
  if (!empuDto.employeeId) {
    alert("Employee ID is required to update details.");
    return;
  }

  this.service.updateEmpPersonalInformation(empuDto).subscribe({
    next: (response) => {
      console.log("Update successful:", response);
      alert("Employee details updated successfully!");
    },
    error: (err) => {
      console.error("Update failed:", err);
      alert("Failed to update employee details.");
    }
  });
}
}
