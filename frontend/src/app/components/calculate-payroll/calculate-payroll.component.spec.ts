import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculatePayrollComponent } from './calculate-payroll.component';

describe('CalculatePayrollComponent', () => {
  let component: CalculatePayrollComponent;
  let fixture: ComponentFixture<CalculatePayrollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculatePayrollComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalculatePayrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
