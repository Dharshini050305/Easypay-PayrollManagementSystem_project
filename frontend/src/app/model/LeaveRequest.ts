export interface LeaveRequest{

leaveId : number;
employeeId: number;
startDate: Date;
endDate: Date;
leaveType: String;
leaveStatus: 'PENDING';
 managerID:{
        managerId :number;
    }
  }