import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollProcessorDashboardComponent } from './payroll-processor-dashboard.component';

describe('PayrollProcessorDashboardComponent', () => {
  let component: PayrollProcessorDashboardComponent;
  let fixture: ComponentFixture<PayrollProcessorDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayrollProcessorDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayrollProcessorDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
