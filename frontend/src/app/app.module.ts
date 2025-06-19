import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SecurityComponent } from './components/security/security.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { AddBenefitsComponent } from './components/add-benefits/add-benefits.component';
import { AddPayrollComponent } from './components/add-payroll/add-payroll.component';
import { AddDeductionComponent } from './components/add-deduction/add-deduction.component';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { PayrollProcessorDashboardComponent } from './components/payroll-processor-dashboard/payroll-processor-dashboard.component';
import { GetAllEmployeeComponent } from './components/get-all-employee/get-all-employee.component';
import { GetBenefitsComponent } from './components/get-benefits/get-benefits.component';
import { GetPayrollComponent } from './components/get-payroll/get-payroll.component';
import { GetDeductionComponent } from './components/get-deduction/get-deduction.component';
import { UpdateBenefitsComponent } from './components/update-benefits/update-benefits.component';
import { UpdateDeductionComponent } from './components/update-deduction/update-deduction.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { UpdatePayrollComponent } from './components/update-payroll/update-payroll.component';
import { ProcessPaymentComponent } from './components/process-payment/process-payment.component';

import { UpdateleaverequestComponent } from './components/updateleaverequest/updateleaverequest.component';
import { GetPaystubsComponent } from './components/get-paystubs/get-paystubs.component';
import { SubmitattendanceComponent } from './components/submitattendance/submitattendance.component';
import { RequestleaveComponent } from './components/requestleave/requestleave.component';

import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { HomeComponent } from './components/home/home.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { ViewEmployeeComponent } from './components/view-employee/view-employee.component';
import { DeductionsComponent } from './components/deductions/deductions.component';
import { BenefitsComponent } from './components/benefits/benefits.component';
import { PayrollComponent } from './components/payroll/payroll.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { PaymentProcessingComponent } from './components/payment-processing/payment-processing.component';
import { CalculatePayrollComponent } from './components/calculate-payroll/calculate-payroll.component';
import { LeaveApprovalComponent } from './components/leave-approval/leave-approval.component';
import { LeaveStatusComponent } from './components/leave-status/leave-status.component';
import { ManagerLeavesComponent } from './components/manager-leaves/manager-leaves.component';




@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    SecurityComponent,
    AddEmployeeComponent,
    AddBenefitsComponent,
    AddPayrollComponent,
    AddDeductionComponent,
    ManagerDashboardComponent,
    AdminDashboardComponent,
    EmployeeDashboardComponent,
    PayrollProcessorDashboardComponent,
    GetAllEmployeeComponent,
    GetBenefitsComponent,
    GetPayrollComponent,
    GetDeductionComponent,
    UpdateBenefitsComponent,
    UpdateDeductionComponent,
    UpdateEmployeeComponent,
    UpdatePayrollComponent,
    ProcessPaymentComponent,
 
    UpdateleaverequestComponent,
    GetPaystubsComponent,
    SubmitattendanceComponent,
    RequestleaveComponent,
  
    HeaderComponent,
    FooterComponent,
    AboutComponent,
    ContactComponent,
    HomeComponent,
    EmployeesComponent,
    ViewEmployeeComponent,
    DeductionsComponent,
    BenefitsComponent,
    PayrollComponent,
    EmployeeDetailsComponent,
    PaymentProcessingComponent,
    CalculatePayrollComponent,
    LeaveApprovalComponent,
    LeaveStatusComponent,
    ManagerLeavesComponent,
  
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
