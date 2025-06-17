import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Payroll } from '../model/Payroll';
import { AttendanceDTO } from '../model/AttendanceDTO';
import { EmpDTO } from '../model/EmpDTO';
import { LeaveDto } from '../model/LeaveDto';
import { EmpuDto } from '../model/EmpuDto';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  [x: string]: any;
  private baseUrl="http://localhost:8080/api/employee";

  constructor(private http:HttpClient) {
   }

   updateEmpPersonalInformation(updatedInfo: EmpuDto): Observable<EmpuDto> {
    const token = localStorage.getItem('authToken');

    if (!token) {
      return throwError(() => new Error('No token found'));
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const url = `${this.baseUrl}/updatepersonalinfo/${updatedInfo.employeeId}`;

    return this.http.put<EmpuDto>(url, updatedInfo, { headers }).pipe(
      catchError(error => {
        console.error('Update failed:', error);
        return throwError(() => error);
      })
    );
  }

   getPayStubs(employeeId:number):Observable<Payroll[]>{
    const token = localStorage.getItem('authToken');  
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); 
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, 
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/paystubs/${employeeId}`;

     return this.http.get<Payroll[]>(url ,{headers});
    
   }

   submitAttendance( attendance:AttendanceDTO):Observable<AttendanceDTO>{
    const token = localStorage.getItem('authToken');  
    if (!token) {
      return new Observable(observer => {
        observer.error('No token found'); 
      });
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, 
      'Content-Type': 'application/json'
    });

    const url=`${this.baseUrl}/submitattendance/${attendance.employeeId}`;

    return this.http.post<AttendanceDTO>(url,attendance,{headers});
   }
  

   requestLeave(leaveRequest: LeaveDto): Observable<LeaveDto> {
  const token = localStorage.getItem('authToken');  
  if (!token) {
    return new Observable(observer => {
      observer.error('No token found'); 
    });
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`, 
    'Content-Type': 'application/json'
  });

  const url = `${this.baseUrl}/requestleave/${leaveRequest.employeeId}`;
  return this.http.post<LeaveDto>(url, leaveRequest, { headers });
}
}