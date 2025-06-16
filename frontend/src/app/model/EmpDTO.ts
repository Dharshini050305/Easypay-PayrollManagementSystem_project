export interface EmpDTO {
    employeeId? : number;
    employeeName: string;         
    email: string;        
    position: string;  
    employeeSalary: number;     
    joinDate: string;         
    userId?: number;           
    deductionId?: number;      
    benefitId?: number;        
    role?: 'ADMIN' | 'EMPLOYEE' | 'MANAGER' | 'PAYROLL_PROCESSOR';        
    managerId?: number;   
    
}