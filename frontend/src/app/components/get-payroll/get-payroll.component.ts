import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-get-payroll',
  templateUrl: './get-payroll.component.html',
  styleUrls: ['./get-payroll.component.css']
})
export class GetPayrollComponent {

  payrollList: Payroll[] = [];
  selectedPayroll: Payroll | null = null;

  constructor(private service: AdminService) {}

  // Fetch all payroll records
  getAllPayrolls() {
    this.service.getAllPayrolls().subscribe(
      (payrolls) => {
        this.payrollList = payrolls;
        console.log(this.payrollList);
      },
      (error) => {
        console.error('Error fetching payrolls:', error);
      }
    );
  }

  // Fetch a single payroll by ID
  getPayrollById(payrollId: number) {
    this.service.getPayrollById(payrollId).subscribe(
      (payroll) => {
        this.selectedPayroll = payroll;
        console.log(this.selectedPayroll);
      },
      (error) => {
        console.error('Error fetching payroll:', error);
        if (error.status === 404) {
          alert(`Payroll with ID ${payrollId} not found.`);
        } else {
          alert('An unexpected error occurred. Please try again later.');
        }
      }
    );
  }

  // Delete payroll by ID
  deletePayroll(payrollId: number) {
    this.service.deletePayroll(payrollId).subscribe(
      () => {
        this.payrollList = this.payrollList.filter(p => p.payrollId !== payrollId);
        console.log(`Payroll with ID ${payrollId} deleted successfully.`);
      },
      (error) => {
        console.error('Error deleting payroll:', error);
        alert('Failed to delete payroll. Please try again.');
      }
    );
  }
  

}
