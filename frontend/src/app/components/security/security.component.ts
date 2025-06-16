import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent {
   userName = '';
  password = '';
  redirectUrl = '';

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => this.redirectUrl = params['redirect'] || '/');
  }

  login() {
    this.http.post('http://localhost:8080/users/login/authenticate',
      { userName: this.userName, password: this.password },
      { responseType: 'text' }) // Handle raw token response
      .subscribe(
        response => {
          console.log('Token received:', response); // Log the token
          localStorage.setItem('authToken', response); // Store the token in localStorage
          console.log('Token stored successfully');
          this.router.navigate([this.redirectUrl]); // Navigate to the target dashboard
        },
        error => {
          console.error('Error during login:', error);
          alert('Invalid  Login credentials !');
        }
      );
  }

}
