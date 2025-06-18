import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit{
 username: string = '';
  showWelcome: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.checkRoute();
    });
  }

  ngOnInit(): void {
    const storedUser = localStorage.getItem('username');
    this.username = storedUser ? storedUser : 'Employee';
    this.checkRoute(); 
  }

  checkRoute(): void {
    const currentUrl = this.router.url;
    this.showWelcome = currentUrl === '/employee-dashboard';
  }
}

