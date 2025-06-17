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

      
        if (err.status === 400 || err.status === 404) {
    
          const errorMessage = err.error.message || err.error; 
          console.log('Error message from backend:', errorMessage);

          if (errorMessage) {
           
            const messages = errorMessage.split(';');
            messages.forEach((message: string) => {  
              alert(message); 
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
