import { Component } from '@angular/core';
import { User } from 'src/app/model/User';
import { RegistrationService } from 'src/app/service/registration.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  constructor(private  service:RegistrationService){

}
 addUser(user: User ){
  
      this.service.addUser(user).subscribe(
  
        (response)=>{  console.log(response)
    
          alert(" registerd ")
        } ,
    
        (err)=>{ console.log(err)}
    
    
    
    );
  
    }
  }
