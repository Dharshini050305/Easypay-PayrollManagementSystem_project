import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payroll } from '../model/Payroll';
import { LeavesDTO } from '../model/LeavesDTO';




@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  private baseUrl='http://localhost:8080/api/manager';

  constructor(private http: HttpClient) {

   }
   
   reviewTeamPayrolls(managerId:number):Observable<Payroll[]>{
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

    const url = `${this.baseUrl}/teampayrolls/${managerId}`;

    return this.http.get<Payroll[]>(url ,{headers});
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


  updateLeaveStatus(managerId: number, leaveId: number, status: string): Observable<any> {
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

    const url = `${this.baseUrl}/approveleave/${managerId}/${leaveId}`;
    return this.http.put<any>(url,{}, {headers});
  }
}