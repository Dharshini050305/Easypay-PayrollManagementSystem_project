import { Component, OnInit } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-get-paystubs',
  templateUrl: './get-paystubs.component.html',
  styleUrls: ['./get-paystubs.component.css']
})
export class GetPaystubsComponent implements OnInit{
  paystubs: Payroll[] = [];
 
  errorMessage: string = '';
  employeeId: number=0; 
  selectedPaystub: Payroll | null = null;
  formSubmitted: boolean = false;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {}

  loadPaystubs(): void {
    if (this.employeeId === null || isNaN(this.employeeId) || this.employeeId <= 0) {
      this.errorMessage = 'Please enter a valid Employee ID.';
      return;
    }

   
    this.employeeService.getPayStubs(this.employeeId).subscribe(
     (data: Payroll[]) => {
        this.paystubs = data;
        console.log("Employee Payroll Fetched");
     
      },
     (error) => {
        this.errorMessage = 'Failed to load pay stubs. Please try again later.';
       console.log(error);
      
      },
    );
  }

  viewPaystubDetails(paystub: Payroll): void {
    this.selectedPaystub = paystub;
  }

  closeDetails(): void {
    this.selectedPaystub = null;
  }

  // Helper method to format currency
  formatCurrency(amount: number): string {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'INR',
    }).format(amount);
  }

  // Helper method to format date
  formatDate(date: Date): string {
    return new Date(date).toLocaleDateString('en-UK', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    });
  }

  onEmployeeIdSubmit(): void {
    this.formSubmitted = true;
    this.loadPaystubs();
  }

}
