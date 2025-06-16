import { Component } from '@angular/core';
import { Benefits } from 'src/app/model/Benefits';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-add-benefits',
  templateUrl: './add-benefits.component.html',
  styleUrls: ['./add-benefits.component.css']
})
export class AddBenefitsComponent {
 
  constructor(private  service:PayrollProcessorService){

  }

  
  submitBenefit(benefits: Benefits){

    this.service.submitBenefit(benefits).subscribe(

      (response: any)=>{  console.log(response)
  
        alert(" Benefits added successfully")
      } ,
  
      (err: any)=>{ console.log(err)}
  
  
  
  );

  }


}
