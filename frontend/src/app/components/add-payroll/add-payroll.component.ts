import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-payroll',
  templateUrl: './add-payroll.component.html',
  styleUrls: ['./add-payroll.component.css']
})
export class AddPayrollComponent {
   constructor(private  service:AdminService){

  }

  submitPayroll(payroll: Payroll){

    this.service.submitPayroll(payroll).subscribe(

      (response: any)=>{  console.log(response)
  
        alert("payroll added successfully")
      } ,
  

      (err: any)=>{ console.log(err)
      alert('Failed to add payroll');
  
      }
  
  );

  }


}
