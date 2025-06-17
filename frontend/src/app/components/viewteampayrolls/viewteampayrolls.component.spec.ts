import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewteampayrollsComponent } from './viewteampayrolls.component';

describe('ViewteampayrollsComponent', () => {
  let component: ViewteampayrollsComponent;
  let fixture: ComponentFixture<ViewteampayrollsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewteampayrollsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewteampayrollsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
