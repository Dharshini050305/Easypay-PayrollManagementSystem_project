import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Benefits } from 'src/app/model/Benefits';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-update-benefits',
  templateUrl: './update-benefits.component.html',
  styleUrls: ['./update-benefits.component.css']
})
export class UpdateBenefitsComponent implements OnInit{
   benefit: Benefits = {
    benefitId: 0,
    benefitName: '',
    benefitAmount: 0
  };

  constructor(
    private service: PayrollProcessorService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const benefitId = +this.route.snapshot.paramMap.get('benefitId')!; // Retrieve the benefit ID from route parameters
    this.service.getBenefitById(benefitId).subscribe((data) => {
      this.benefit = data; // Initialize the benefit object with the fetched data
    });
  }

  updateBenefit(): void {
  if (this.benefit && this.benefit.benefitId) {
    this.service.updateBenefit(this.benefit.benefitId, this.benefit).subscribe(
      () => {
        console.log('Benefit updated successfully');
        alert('Benefit updated successfully!');
      },
      (error) => {
        console.error('Error updating benefit:', error);
        alert('Failed to update benefit. Please try again.');
      }
    );
  } else {
    alert('Benefit data is incomplete or invalid.');
  }
}
}
