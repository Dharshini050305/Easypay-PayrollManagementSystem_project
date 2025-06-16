import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDeductionComponent } from './get-deduction.component';

describe('GetDeductionComponent', () => {
  let component: GetDeductionComponent;
  let fixture: ComponentFixture<GetDeductionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetDeductionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetDeductionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
