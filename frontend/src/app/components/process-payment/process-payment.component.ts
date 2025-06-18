import { Component } from '@angular/core';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-process-payment',
  templateUrl: './process-payment.component.html',
  styleUrls: ['./process-payment.component.css']
})
export class ProcessPaymentComponent {
   employeeId!: number;
  payrollDate!: string;
  message: string = '';
  grossPay?: number;
  isSuccess: boolean = false;

  constructor(private payrollService: PayrollProcessorService) {}

  onProcessPayment(): void {
    this.payrollService.processPayments(this.employeeId, this.payrollDate).subscribe({
      next: (response) => {
        this.message = response.message;
        this.grossPay = response.grossPay;
        this.isSuccess = true;
      },
      error: (error) => {
        console.error('Error:', error);
        this.message = 'Failed to process payroll payment.';
        this.isSuccess = false;
      }
    });
  }
}