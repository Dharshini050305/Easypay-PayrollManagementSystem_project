import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit{
  username: string = '';
  showWelcome: boolean = true;

  constructor(private router: Router) {
    // Listen to route changes
    this.router.events.subscribe(() => {
      this.checkRoute();
    });
  }

  ngOnInit(): void {
    const storedUser = localStorage.getItem('username');
    this.username = storedUser ? storedUser : 'Admin';
    this.checkRoute(); // check on load
  }

  checkRoute(): void {
    const currentUrl = this.router.url;
    // Only show on exact dashboard
    this.showWelcome = currentUrl === '/admin-dashboard';
  }
}
  


