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


const routes: Routes = [
   { path: 'login', component: SecurityComponent },
  { path: 'register', component: RegistrationComponent },
   { path: 'admin-dashboard', component: AdminDashboardComponent, children: [
    { path: 'add-employee', component: AddEmployeeComponent },
]},
{path: 'payroll-processor-dashboard', component: PayrollProcessorDashboardComponent, children:[
  { path: 'add-benefits', component: AddBenefitsComponent },
  { path: 'get-benefits', component: GetBenefitsComponent },
  { path: 'editben/:benifitId', component: UpdateBenefitsComponent},
  { path: 'add-deduction', component: AddDeductionComponent },
  { path: 'get-deduction', component: GetDeductionComponent },
  { path: 'editded/:deductionId', component: UpdateDeductionComponent},
]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
   
