import { Component } from '@angular/core';
import { Deductions } from 'src/app/model/Deductions';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-get-deduction',
  templateUrl: './get-deduction.component.html',
  styleUrls: ['./get-deduction.component.css']
})
export class GetDeductionComponent {
   selectedDeduction: Deductions| null = null;
  deductionList:Deductions[] = [];

  constructor(private  service:PayrollProcessorService){

  }



  getAllDeductions(){
    this.service.getAllDeductions().subscribe(
      (deduction) => { 
        this.deductionList =deduction;
        console.log(this.deductionList);
      },
      (err) => { 
        console.log(err); 
      }
    );
  }



   getDeductionById(deductionId: number) {
    this.service.getDeductionById(deductionId).subscribe(
      (deduction) => {
        this.selectedDeduction = deduction;
        console.log(this.selectedDeduction);
      },
      (err) => {
        console.error('Error fetching employee by ID:', err);
                 // Handle specific error status (e.g., 404 Not Found)
      if (err.status === 404) {
        alert(`deduction with ID ${deductionId} not found.`);
      } else {
        // Handle other errors (e.g., 500 Internal Server Error)
        alert('An unexpected error occurred. Please try again later.');
      }
      }
    );
  }


  deleteDeduction(deductionId: number){
    this.service.deleteDeduction(deductionId).subscribe(
      (response) => {
        // Handle successful deletion (e.g., refresh the list or remove the report from reportList)
        this.deductionList = this.deductionList.filter(report => report.deductionId !== deductionId);
        console.log('Report deleted successfully');
      },
      (error) => {
        // Handle error (e.g., show an error message)
        console.error('Error deleting report:', error);
      }
    );
  }

}
