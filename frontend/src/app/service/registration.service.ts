import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

   
   private baseURL: string = 'http://localhost:8080/users/registration/new';

   constructor(private http: HttpClient) {}
 
  
   addUser(user: User):Observable<User> {
    return this.http.post<User>(this.baseURL, user, { responseType: 'text' as 'json' });
  }


}