import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateleaverequestComponent } from './updateleaverequest.component';

describe('UpdateleaverequestComponent', () => {
  let component: UpdateleaverequestComponent;
  let fixture: ComponentFixture<UpdateleaverequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateleaverequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateleaverequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
