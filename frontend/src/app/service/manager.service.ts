import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Payroll } from '../model/Payroll';
import { LeavesDTO } from '../model/LeavesDTO';
import { LeaveRequest } from '../model/LeaveRequest';
import { LeaveRequestDTO } from '../model/LeaveRequestDTO';




@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  private baseUrl='http://localhost:8080/api/manager';

  constructor(private http: HttpClient) {

   }
   
  

getAllLeavesByManagerId(managerId: number): Observable<LeavesDTO[]> {
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

  const url = `${this.baseUrl}/leave-requests/${managerId}`; // Adjust this URL based on your API
  return this.http.get<LeavesDTO[]>(url, { headers });
}


  updateLeaveStatus(managerId: number, leaveId: number, status: string): Observable<LeavesDTO> {
  const token = localStorage.getItem('authToken');
  
  if (!token) {
    return throwError(() => new Error('No auth token found'));
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  const params = new HttpParams().set('status', status);

  const url = `${this.baseUrl}/approveleave/${managerId}/${leaveId}`;

  return this.http.put<LeavesDTO>(url, null, { headers, params });
}
getLeavesByManagerId(managerId: number): Observable<LeaveRequestDTO[]> {
  const token = localStorage.getItem('authToken');
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  const url = `${this.baseUrl}/manager-leaves/${managerId}`;

  return this.http.get<LeaveRequestDTO[]>(url, { headers });
}
}