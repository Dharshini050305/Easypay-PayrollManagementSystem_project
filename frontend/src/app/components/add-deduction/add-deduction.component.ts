import { Component } from '@angular/core';
import { Deductions } from 'src/app/model/Deductions';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-add-deduction',
  templateUrl: './add-deduction.component.html',
  styleUrls: ['./add-deduction.component.css']
})
export class AddDeductionComponent {
   constructor(private  service:PayrollProcessorService){

  }


  submitDeduction(deduction: Deductions){

    this.service.submitDeduction(deduction).subscribe(

      (response: any)=>{  console.log(response)
  
        alert(" Deduction added successfully")
      } ,
  
      (err: any)=>{ console.log(err)}
  
  
  
  );

  }


}
