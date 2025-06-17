import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitattendanceComponent } from './submitattendance.component';

describe('SubmitattendanceComponent', () => {
  let component: SubmitattendanceComponent;
  let fixture: ComponentFixture<SubmitattendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitattendanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitattendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
