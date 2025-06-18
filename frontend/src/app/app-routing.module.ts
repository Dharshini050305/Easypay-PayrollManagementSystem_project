import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';
import { SecurityComponent } from './components/security/security.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { AddBenefitsComponent } from './components/add-benefits/add-benefits.component';
import { AddDeductionComponent } from './components/add-deduction/add-deduction.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { GetBenefitsComponent } from './components/get-benefits/get-benefits.component';
import { GetDeductionComponent } from './components/get-deduction/get-deduction.component';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { PayrollProcessorDashboardComponent } from './components/payroll-processor-dashboard/payroll-processor-dashboard.component';
import { UpdateBenefitsComponent } from './components/update-benefits/update-benefits.component';
import { UpdateDeductionComponent } from './components/update-deduction/update-deduction.component';
import { GetAllEmployeeComponent } from './components/get-all-employee/get-all-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { AddPayrollComponent } from './components/add-payroll/add-payroll.component';
import { GetPayrollComponent } from './components/get-payroll/get-payroll.component';
import { UpdatePayrollComponent } from './components/update-payroll/update-payroll.component';
import { ProcessPaymentComponent } from './components/process-payment/process-payment.component';
import { ViewteampayrollsComponent } from './components/viewteampayrolls/viewteampayrolls.component';
import { UpdateleaverequestComponent } from './components/updateleaverequest/updateleaverequest.component';
import { GetPaystubsComponent } from './components/get-paystubs/get-paystubs.component';
import { SubmitattendanceComponent } from './components/submitattendance/submitattendance.component';
import { RequestleaveComponent } from './components/requestleave/requestleave.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { HomeComponent } from './components/home/home.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { ViewEmployeeComponent } from './components/view-employee/view-employee.component';
import { BenefitsComponent } from './components/benefits/benefits.component';
import { DeductionsComponent } from './components/deductions/deductions.component';
import { PayrollComponent } from './components/payroll/payroll.component';


const routes: Routes = [
 { path: '', component: HomeComponent   }, 
   { path: 'login', component: SecurityComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'home', component: HomeComponent },
 { path: 'admin-dashboard', component: AdminDashboardComponent },
{ path: 'manager-dashboard', component: ManagerDashboardComponent },
{ path: 'employee-dashboard', component: EmployeeDashboardComponent },

{ path: 'payroll-processor-dashboard', component: PayrollProcessorDashboardComponent },

{ path: 'admin-dashboard', component:AdminDashboardComponent, children: [
  { path: 'employees', component: EmployeesComponent },
    { path: 'add-employee', component: AddEmployeeComponent },
    { path: 'get-all-employee', component: GetAllEmployeeComponent },
    { path: 'view-employee', component: ViewEmployeeComponent },
    { path: 'edit/:employeeId', component: UpdateEmployeeComponent },
  { path: 'payroll', component: PayrollComponent },
    { path: 'add-payroll', component: AddPayrollComponent },
    { path: 'get-payroll', component: GetPayrollComponent },
    { path: 'editpay/:payrollId', component: UpdatePayrollComponent },


]},
{path: 'payroll-processor-dashboard', component: PayrollProcessorDashboardComponent, children:[
  { path: 'benefits', component: BenefitsComponent },
  { path: 'add-benefits', component: AddBenefitsComponent },
  { path: 'get-benefits', component: GetBenefitsComponent },
  { path: 'editben/:benefitId', component: UpdateBenefitsComponent},
    { path: 'deductions', component: DeductionsComponent },
  { path: 'add-deduction', component: AddDeductionComponent },
  { path: 'get-deduction', component: GetDeductionComponent },
  { path: 'editded/:deductionId', component: UpdateDeductionComponent},
  { path: 'process-payment', component: ProcessPaymentComponent }
]

},
{ path: 'manager-dashboard', component: ManagerDashboardComponent, children:[
  { path:'viewteampayrolls',component:ViewteampayrollsComponent },
  { path:'updateleaverequest',component:UpdateleaverequestComponent },
  
]
},

{ path: 'employee-dashboard', component: EmployeeDashboardComponent, children:[
  { path:'updateempdetails',component:UpdateEmployeeComponent },
   { path:'getpaystubs',component:GetPaystubsComponent },
  { path:'submitattendance',component:SubmitattendanceComponent },
  { path: 'requestleave',component:RequestleaveComponent}
]


},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
   
