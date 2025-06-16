import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Employee } from 'src/app/model/Employee';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {
  constructor(private service: AdminService) { }

  submitEmployee(employee: Employee) {
    this.service.submitEmployee(employee).subscribe(
      (response) => {
        console.log(response);
        alert("Employee added successfully");
      },
      (err: HttpErrorResponse) => {  // Type the error as HttpErrorResponse
        console.error(err);

        // Check if the error contains a message with the ID that is not found
        if (err.status === 400 || err.status === 404) {
          // Assuming the backend sends a detailed error message like: "User not found with ID: 1"
          const errorMessage = err.error.message || err.error; // Fallback to err.error if no message property
          console.log('Error message from backend:', errorMessage);

          if (errorMessage) {
            // Split multiple error messages if they are provided (e.g., user not found, deductions not found)
            const messages = errorMessage.split(';');
            messages.forEach((message: string) => {  // Explicitly typing message as a string
              alert(message); // Show each error message in an alert
            });
          } else {
            alert("An unexpected error occurred. Please try again later.");
          }
        } else {
          alert("An unexpected error occurred. Please try again later.");
        }
      }
    );
  }

}
