export interface LeaveDto{

    employeeId:number;
    startDate:Date;
    endDate:Date;
    leaveType:string;
    managerID:{
        managerId :number;
    }
}