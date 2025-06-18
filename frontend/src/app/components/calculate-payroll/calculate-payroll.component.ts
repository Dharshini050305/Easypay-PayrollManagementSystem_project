import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { PayrollProcessorService } from 'src/app/service/payroll-processor.service';

@Component({
  selector: 'app-calculate-payroll',
  templateUrl: './calculate-payroll.component.html',
  styleUrls: ['./calculate-payroll.component.css']
})
export class CalculatePayrollComponent {
  employeeId!: number;
  payrollDate!: string; // bound to <input type="date">, will be a string
  payrollResult?: Payroll;
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private payrollService: PayrollProcessorService) {}

  onCalculate(): void {
    this.errorMessage = '';
    this.payrollResult = undefined;

    if (!this.employeeId || !this.payrollDate) {
      this.errorMessage = 'Please enter both Employee ID and Payroll Date.';
      return;
    }

    this.isLoading = true;

    // Convert payrollDate string to Date object
    const dateObj = new Date(this.payrollDate);
    const dateString = dateObj.toISOString().split('T')[0]; // format yyyy-MM-dd

    this.payrollService.calculatePayroll(this.employeeId, dateString).subscribe({
      next: (payroll) => {
        this.payrollResult = payroll;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Error calculating payroll: ' + err.message;
        this.isLoading = false;
      }
    });
  }
}