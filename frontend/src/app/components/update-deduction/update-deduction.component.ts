import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Deductions } from 'src/app/model/Deductions';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-update-deduction',
  templateUrl: './update-deduction.component.html',
  styleUrls: ['./update-deduction.component.css']
})
export class UpdateDeductionComponent implements OnInit {
  deduction: Deductions = {
    deductionId: 0,
    deductionName: '',
    deductionAmount: 0
  };

  constructor(
    private service: PayrollProcessorService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const deductionId = +this.route.snapshot.paramMap.get('deductionId')!;
    this.service.getDeductionById(deductionId).subscribe((data) => {
      this.deduction = data;
    });
  }

 updateDeduction(): void {
  if (this.deduction && this.deduction.deductionId) {
    this.service.updateDeduction(this.deduction.deductionId, this.deduction).subscribe(
      () => {
        console.log('Deduction updated successfully');
        alert('Deduction updated successfully!');
      },
      (error) => {
        console.error('Error updating deduction:', error);
        alert('Failed to update deduction. Please try again.');
      }
    );
  } else {
    alert('Deduction data is incomplete or invalid.');
  }

}
}
