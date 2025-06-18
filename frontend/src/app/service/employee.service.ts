import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Payroll } from '../model/Payroll';
import { AttendanceDTO } from '../model/AttendanceDTO';
import { EmpDTO } from '../model/EmpDTO';
import { LeaveDto } from '../model/LeaveDto';
import { EmpuDto } from '../model/EmpuDto';
import { LeaveRequest } from '../model/LeaveRequest';
import { LeaveRequestDTO } from '../model/LeaveRequestDTO';

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
  

  

  requestLeave(leaveRequest: LeaveRequest): Observable<LeaveRequest> {
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

  const url = `http://localhost:8080/api/employee/requestleave/${leaveRequest.employeeId}`;

  return this.http.post<LeaveRequest>(url, leaveRequest, { headers });
}

getLeaveStatusByEmployeeId(employeeId: number): Observable<LeaveRequestDTO[]> {
  const token = localStorage.getItem('authToken');
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  const url = `${this.baseUrl}/leave-status/${employeeId}`;

  return this.http.get<LeaveRequestDTO[]>(url, { headers });
}
}
