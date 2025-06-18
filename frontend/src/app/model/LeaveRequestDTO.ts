export interface LeaveRequestDTO {
  leaveId: number;
  employeeId: number;
  startDate: string;
  endDate: string;
  leaveType: string;
  leaveStatus: string;
  managerId: number;
}