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
import { ViewteampayrollsComponent } from './components/viewteampayrolls/viewteampayrolls.component';
import { UpdateleaverequestComponent } from './components/updateleaverequest/updateleaverequest.component';
import { GetPaystubsComponent } from './components/get-paystubs/get-paystubs.component';
import { SubmitattendanceComponent } from './components/submitattendance/submitattendance.component';
import { RequestleaveComponent } from './components/requestleave/requestleave.component';
import { UpdateempdetailsComponent } from './components/updateempdetails/updateempdetails.component';


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
    ViewteampayrollsComponent,
    UpdateleaverequestComponent,
    GetPaystubsComponent,
    SubmitattendanceComponent,
    RequestleaveComponent,
    UpdateempdetailsComponent
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
