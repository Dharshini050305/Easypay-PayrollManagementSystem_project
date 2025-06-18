import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Benefits } from '../model/Benefits';
import { Deductions } from '../model/Deductions';
import { Payroll } from '../model/Payroll';



@Injectable({
  providedIn: 'root'
})
export class PayrollProcessorService {


  baseURL:string = "http://localhost:8080/api/payroll/";

  constructor(private  http:HttpClient) {

   }

   private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    if (!token) {
      throw new Error('No token found');
    }
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }
//calulate payroll
  calculatePayroll(employeeId: number, payrollDate: string): Observable<Payroll> {
  const token = localStorage.getItem('authToken');

  const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<Payroll>(
    `${this.baseURL}calculate/${employeeId}/${payrollDate}`,
    { headers }
  );
}
  processPayments(employeeId: number, payrollDate: string): Observable<{ message: string; grossPay: number }> {
  const token = localStorage.getItem('authToken');

  const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
  });

  return this.http.post<{ message: string; grossPay: number }>(
    `${this.baseURL}payment/process/${employeeId}/${payrollDate}`,
    {},
    { headers } 
  );
}



// ---------------------------------------Benefits  service-----------------------------------

submitBenefit(benefits: Benefits): Observable<Benefits> {
  const headers = this.getAuthHeaders();
  return this.http.post<Benefits>(`${this.baseURL}benefits/add`, benefits, { headers });
}

getAllBenefits(): Observable<Benefits[]> {
  const headers = this.getAuthHeaders();
  return this.http.get<Benefits[]>(`${this.baseURL}benefits/all`, { headers });
}

getBenefitById(id: number): Observable<Benefits> {
  const headers = this.getAuthHeaders();
  return this.http.get<Benefits>(`${this.baseURL}benefits/${id}`, { headers });
}

deleteBenefit(id: number): Observable<void> {
  const headers = this.getAuthHeaders();
  return this.http.delete<void>(`${this.baseURL}benefits/delete/${id}`, { headers });
}

updateBenefit(benefitId: number, benefit: Benefits): Observable<Benefits> {
  const headers = this.getAuthHeaders();
  return this.http.put<Benefits>(`${this.baseURL}benefits/update/${benefitId}`, benefit, { headers });
}
// ---------------------------------------Deduction  service------------------------------------

    // Get All Deductions
    getAllDeductions(): Observable<Deductions[]> {
      const headers = this.getAuthHeaders();
      return this.http.get<Deductions[]>(`${this.baseURL}deductions/all`, { headers });
    }
  
    // Get Deduction by ID
    getDeductionById(deductionId: number): Observable<Deductions> {
      const headers = this.getAuthHeaders();
      return this.http.get<Deductions>(`${this.baseURL}deductions/${deductionId}`, { headers });
    }
  
    // Update Deduction
    updateDeduction(deductionId: number, deduction: Deductions): Observable<Deductions> {
      const headers = this.getAuthHeaders();
      return this.http.put<Deductions>(`${this.baseURL}deductions/update/${deductionId}`, deduction, { headers });
    }
  
    // Delete Deduction
    deleteDeduction(deductionId: number): Observable<void> {
      const headers = this.getAuthHeaders();
      return this.http.delete<void>(`${this.baseURL}deductions/delete/${deductionId}`, { headers });
    }

    submitDeduction(deduction: Deductions): Observable<Deductions> {
      const headers = this.getAuthHeaders();
      return this.http.post<Deductions>(`${this.baseURL}deductions/add`, deduction, { headers });
    }
      processPayment(employeeId: number, payrollDate: string): Observable<string> {
      const headers = this.getAuthHeaders();
      return this.http.post<string>(`${this.baseURL}payment/process/${employeeId}/${payrollDate}`, null,{ headers });
    }
    

}