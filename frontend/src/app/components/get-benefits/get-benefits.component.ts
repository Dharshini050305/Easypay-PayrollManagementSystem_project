import { Component } from '@angular/core';
import { Benefits } from 'src/app/model/Benefits';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-get-benefits',
  templateUrl: './get-benefits.component.html',
  styleUrls: ['./get-benefits.component.css']
})
export class GetBenefitsComponent {
   selectedBenefit: Benefits | null = null;
  benefitList: Benefits[] = [];

  constructor(private  service:PayrollProcessorService){

  }

  getAllBenefits(){
    this.service.getAllBenefits().subscribe(
      (benefits) => { 
        this.benefitList = benefits;
        console.log(this.benefitList);
      },
      (err) => { 
        console.log(err); 
      }
    );
  }



  getBenefitById(id: number) {
    this.service.getBenefitById(id).subscribe(
      (benefits) => {
        this.selectedBenefit = benefits;
        console.log(this.selectedBenefit);
      },
      (err) => {
        console.error('Error fetching employee by ID:', err);
                 // Handle specific error status (e.g., 404 Not Found)
      if (err.status === 404) {
        alert(`Benefits with ID ${id} not found.`);
      } else {
        // Handle other errors (e.g., 500 Internal Server Error)
        alert('An unexpected error occurred. Please try again later.');
      }
      }
    );
  }


  deleteBenefit(benefitId: number){
    this.service.deleteBenefit(benefitId).subscribe(
      (response) => {
        // Handle successful deletion (e.g., refresh the list or remove the report from reportList)
        this.benefitList = this.benefitList.filter(report => report.benefitId !== benefitId);
        console.log('Report deleted successfully');
      },
      (error) => {
        // Handle error (e.g., show an error message)
        console.error('Error deleting report:', error);
      }
    );
  }


}
