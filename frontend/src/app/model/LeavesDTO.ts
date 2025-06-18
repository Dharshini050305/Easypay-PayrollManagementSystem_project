export interface LeavesDTO{
    leaveId:number;
    employeeId:number;
    startDate:Date;
    endDate:Date;
    leaveType:string;
    leaveStatus:'Pending' | 'Approved' | 'Rejected';
    managerId: number;
  

}