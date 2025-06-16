import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payroll } from '../model/Payroll';
import { AttendanceDTO } from '../model/AttendanceDTO';
import { EmpDTO } from '../model/EmpDTO';
import { LeavesDTO } from '../model/LeavesDTO';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl="http://localhost:8080/api/employee";

  constructor(private http:HttpClient) {
   }

   updateEmpPersonalInformation( updatedInfo: EmpDTO):Observable<EmpDTO>{ 
    const token = localStorage.getItem('authToken');  // Retrieve token from localStorage
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); // Handle case where token is not found
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, // Add token to the Authorization header
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/updatepersonalinfo/${updatedInfo.employeeId}`;

    return this.http.put<EmpDTO>(url,updatedInfo,{headers});
    
   }

   getPayStubs(employeeId:number):Observable<Payroll[]>{
    const token = localStorage.getItem('authToken');  // Retrieve token from localStorage
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); // Handle case where token is not found
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, // Add token to the Authorization header
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/paystubs/${employeeId}`;

     return this.http.get<Payroll[]>(url ,{headers});
    
   }

   submitAttendance( attendance:AttendanceDTO):Observable<AttendanceDTO>{
    const token = localStorage.getItem('authToken');  // Retrieve token from localStorage
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); // Handle case where token is not found
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, // Add token to the Authorization header
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/submitattendance/${attendance.employeeId}`;

    return this.http.post<AttendanceDTO>(url,attendance,{headers});
   }
  

   requestLeave( leaveRequest:LeavesDTO):Observable<LeavesDTO>{
    const token = localStorage.getItem('authToken');  // Retrieve token from localStorage
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); // Handle case where token is not found
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, // Add token to the Authorization header
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/requestleave/${leaveRequest.employeeId}`;
    return this.http.post<LeavesDTO>(url, leaveRequest,{headers});    
   }

}