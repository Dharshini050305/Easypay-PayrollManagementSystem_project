import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePayrollComponent } from './update-payroll.component';

describe('UpdatePayrollComponent', () => {
  let component: UpdatePayrollComponent;
  let fixture: ComponentFixture<UpdatePayrollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatePayrollComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatePayrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
