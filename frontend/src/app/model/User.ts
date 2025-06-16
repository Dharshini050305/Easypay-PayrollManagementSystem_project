export interface User {
  userId?: number;         
  userName: string;
  password: string;
  role?: 'ADMIN' | 'EMPLOYEE' | 'MANAGER' | 'PAYROLL_PROCESSOR'; 
}