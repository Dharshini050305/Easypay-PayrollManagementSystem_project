import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateempdetailsComponent } from './updateempdetails.component';

describe('UpdateempdetailsComponent', () => {
  let component: UpdateempdetailsComponent;
  let fixture: ComponentFixture<UpdateempdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateempdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateempdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
