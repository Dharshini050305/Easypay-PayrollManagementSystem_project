export interface LeaveRequest {
  leaveId?: number;         
  employeeId: number;
  startDate: Date;
  endDate: Date;
  leaveType: string;
  leaveStatus?: 'PENDING' | 'APPROVED';  
  managerId: number;
}