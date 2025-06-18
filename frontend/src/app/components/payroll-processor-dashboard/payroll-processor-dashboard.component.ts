import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payroll-processor-dashboard',
  templateUrl: './payroll-processor-dashboard.component.html',
  styleUrls: ['./payroll-processor-dashboard.component.css']
})
export class PayrollProcessorDashboardComponent implements OnInit{
  username: string = '';
    showWelcome: boolean = true;
  
    constructor(private router: Router) {
      this.router.events.subscribe(() => {
        this.checkRoute();
      });
    }
  
    ngOnInit(): void {
      const storedUser = localStorage.getItem('username');
      this.username = storedUser ? storedUser : 'Payroll Processor';
      this.checkRoute(); 
    }
  
    checkRoute(): void {
      const currentUrl = this.router.url;
      this.showWelcome = currentUrl === '/payroll-processor-dashboard';
    }

}
