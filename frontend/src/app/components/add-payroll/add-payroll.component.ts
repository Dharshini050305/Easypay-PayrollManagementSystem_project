import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-payroll',
  templateUrl: './add-payroll.component.html',
  styleUrls: ['./add-payroll.component.css']
})
export class AddPayrollComponent {
  constructor(private adminService: AdminService) {}

  submitPayroll(formValue: any): void {
    const payrollPayload: Payroll = {
      benefits: Number(formValue.benefits),
      deductions: Number(formValue.deductions),
      grossPay: Number(formValue.grossPay),
      netPay: Number(formValue.netPay),
      payrollDate: new Date(formValue.payrollDate),
      
        employeeId: Number(formValue.employeeId),
      
    };

    this.adminService.submitPayroll(payrollPayload).subscribe({
      next: (response) => {
        console.log('Payroll added successfully:', response);
        alert('Payroll added successfully!');
      },
      error: (error) => {
        console.error('Error adding payroll:', error);
        alert('Failed to add payroll.');
      }
    });
  }
}











