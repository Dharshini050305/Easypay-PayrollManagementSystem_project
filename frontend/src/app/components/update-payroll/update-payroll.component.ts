import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Payroll } from 'src/app/model/Payroll';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-update-payroll',
  templateUrl: './update-payroll.component.html',
  styleUrls: ['./update-payroll.component.css']
})
export class UpdatePayrollComponent implements OnInit {
  payroll: Payroll = {
   payrollId: 0,
    benefits: 0,
    deductions: 0,
    grossPay: 0,
    netPay: 0,
    payrollDate:new Date(),
    
    employeeId: 0
  
  };

  constructor(
    private service: AdminService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const payrollId = +this.route.snapshot.paramMap.get('payrollId')!;
    this.service.getPayrollById(payrollId).subscribe((data) => {
      this.payroll = data;
    });
  }

  updatePayroll(): void {
  if (this.payroll && this.payroll.payrollId && this.payroll.employeeId) {
    this.service.updatePayroll(this.payroll.payrollId, this.payroll).subscribe(
      (updatedPayroll) => {
        this.payroll = updatedPayroll;   
        alert('Payroll updated successfully!');
      },
      (error) => {
        console.error('Error updating payroll:', error);
        alert('Failed to update payroll. Please try again.');
      }
    );
  } else {
    alert('Payroll data is incomplete. Employee ID is missing.');
  }
}
}