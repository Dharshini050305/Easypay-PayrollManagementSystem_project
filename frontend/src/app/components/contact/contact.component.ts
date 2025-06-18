import { Component } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {

  onSubmit(formValue: any) {
  console.log('Contact form submitted:', formValue);
  alert('Thank you for contacting EasyPay! We will get back to you soon.');
  // Here you can add real submission logic (e.g., call backend API)
}
}
