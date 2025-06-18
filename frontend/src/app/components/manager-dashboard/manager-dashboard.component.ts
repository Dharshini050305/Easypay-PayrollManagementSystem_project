import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css']
})
export class ManagerDashboardComponent {
username: string = '';
    showWelcome: boolean = true;
  
    constructor(private router: Router) {
      this.router.events.subscribe(() => {
        this.checkRoute();
      });
    }
  
    ngOnInit(): void {
      const storedUser = localStorage.getItem('username');
      this.username = storedUser ? storedUser : 'Manager';
      this.checkRoute(); 
    }
  
    checkRoute(): void {
      const currentUrl = this.router.url;
      this.showWelcome = currentUrl === '/manager-dashboard';
    }
}
