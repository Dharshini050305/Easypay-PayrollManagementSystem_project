import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../model/Employee';
import { Payroll } from '../model/Payroll';




@Injectable({
  providedIn: 'root'
})
export class AdminService {

  baseURL = "http://localhost:8080/api/admin/";

  constructor(private  http:HttpClient) {

   }

 
  // --------------------------employee service--------------------

  submitEmployee(employee: Employee): Observable<Employee> {
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

    const url = `${this.baseURL}employee/add`;
    return this.http.post<Employee>(url, employee, { headers });
  }
   getAllEmployees(): Observable<Employee[]> {
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

    const url = `${this.baseURL}employees`; 
    return this.http.get<Employee[]>(url, { headers });
  }
     
  getEmployeeById(employeeId: number): Observable<Employee> {
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

    const url = `${this.baseURL}employee/${employeeId}`;
    return this.http.get<Employee>(url, { headers });
  }

    
    
  deleteEmployee(employeeId: number): Observable<void> {
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

    const url = `${this.baseURL}employee/delete/${employeeId}`;
    return this.http.delete<void>(url, { headers });
  }


updateEmployee(employeeId: number, employee: Employee): Observable<Employee> {
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

  const url = `${this.baseURL}employee/update/${employeeId}`;
  return this.http.put<Employee>(url, employee, { headers });

}
//-----------------------------------------Payroll----------------------------------------------------------
submitPayroll(payroll: Payroll): Observable<Payroll> {
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

  return this.http.post<Payroll>(`${this.baseURL}payroll/add`, payroll, { headers });
}

 getAllPayrolls(): Observable<Payroll[]> {
  const token = localStorage.getItem('authToken');
  if (!token) {
    return new Observable(observer => {
      observer.error('No token found');
    });
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<Payroll[]>(`${this.baseURL}payroll`, { headers });
}
    
     
getPayrollById(payrollId: number): Observable<Payroll> {
  const token = localStorage.getItem('authToken');
  if (!token) {
    return new Observable(observer => {
      observer.error('No token found');
    });
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  const url = `${this.baseURL}payroll/${payrollId}`;
  return this.http.get<Payroll>(url, { headers });
}
    
    
deletePayroll(payrollId: number): Observable<void> {
  const token = localStorage.getItem('authToken');
  if (!token) {
    return new Observable(observer => {
      observer.error('No token found');
    });
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  const url = `${this.baseURL}payroll/delete/${payrollId}`;
  return this.http.delete<void>(url, { headers });
}


updatePayroll(payrollId: number, payroll: Payroll): Observable<Payroll> {
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

  const url = `${this.baseURL}payroll/update/${payrollId}`;
  return this.http.put<Payroll>(url, payroll, { headers });
}
}
