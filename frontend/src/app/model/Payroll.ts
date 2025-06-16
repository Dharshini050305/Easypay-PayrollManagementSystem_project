export interface Payroll {
  payrollId?: number;       
  benefits: number;
  deductions: number;
  grossPay: number;
  netPay: number;
  payrollDate: string;      
  employeeId?: number;
}