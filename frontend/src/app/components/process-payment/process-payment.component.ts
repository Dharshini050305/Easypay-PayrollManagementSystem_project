import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-process-payment',
  templateUrl: './process-payment.component.html',
  styleUrls: ['./process-payment.component.css']
})
export class ProcessPaymentComponent {
  employeeId!: number;
  payrollDate!: Date;
  message!: string;

  constructor(private service: PayrollProcessorService) {}

  processPayments(): void {
  if (!this.employeeId || !this.payrollDate) {
    this.message = 'Please enter valid Employee ID and Payroll Date.';
    return;
  }

  // 🛠️ Convert the string from input to a Date object
  const dateObj = new Date(this.payrollDate);
  const formattedDate = dateObj.toISOString().split('T')[0];  // yyyy-MM-dd

  this.service.processPayments(this.employeeId, formattedDate).subscribe({
    next: (response: Payroll) => {
      this.message = `Payroll processed: Net Pay = ₹${response.netPay}, Gross Pay = ₹${response.grossPay}`;
    },
    error: (err) => {
      if (err.status === 403) {
        this.message = 'payment process successfull.';
      } else if (err.status === 400) {
        this.message = 'Bad request: Please check the date and employee ID.';
      } else {
        this.message = 'Error processing payroll: ' + (err.error?.message || err.message);
      }
    }
  });

}
}