import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPaystubsComponent } from './get-paystubs.component';

describe('GetPaystubsComponent', () => {
  let component: GetPaystubsComponent;
  let fixture: ComponentFixture<GetPaystubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetPaystubsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetPaystubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
