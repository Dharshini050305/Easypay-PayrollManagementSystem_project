import { Component } from '@angular/core';
import { Payroll } from 'src/app/model/Payroll';
import { ManagerService } from 'src/app/service/manager.service';

@Component({
  selector: 'app-viewteampayrolls',
  templateUrl: './viewteampayrolls.component.html',
  styleUrls: ['./viewteampayrolls.component.css']
})
export class ViewteampayrollsComponent {
   managerId: number | null = null;  // The manager's ID entered by the user
  payrolls: Payroll[] = [];      // Array to store the payroll data
  errorMessage: string = '';        // Error message if fetching payrolls fails
  formSubmitted: boolean = false;   // Flag to show validation messages after form submission

  constructor(private managerService: ManagerService) {}

  // Method to fetch payrolls based on Manager ID
  fetchTeamPayrolls(): void {
    this.formSubmitted = true;  // Set formSubmitted to true to show validation messages

    // Validate Manager ID
    if (this.managerId === null || this.managerId <= 0) {
      this.errorMessage = 'Please enter a valid Manager ID.';
      this.payrolls = [];  // Clear payrolls in case of invalid ID
      return;
    }

    this.errorMessage = '';  // Clear any previous error message
    this.managerService.reviewTeamPayrolls(this.managerId).subscribe({
      next: (data: Payroll[]) => {
        this.payrolls = data;  // Assign the fetched data to the payrolls array
      },
      error: (error: any) => {
      
        this.payrolls = [];  // Clear payrolls if error occurs
      }
    });
  }

}
