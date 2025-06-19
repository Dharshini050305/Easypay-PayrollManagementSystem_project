import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeComponent ],
       imports: [RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should display the Easypay logo', () => {
    const logo = fixture.debugElement.query(By.css('img.logo-img'));
    expect(logo).toBeTruthy();
    expect(logo.nativeElement.getAttribute('src')).toContain('assets/easypay.png');
  });

  it('should display welcome heading', () => {
    const heading = fixture.debugElement.query(By.css('h1'));
    expect(heading.nativeElement.textContent).toContain('Welcome to Easypay!');
  });

  it('should display 4 dashboard buttons', () => {
    const buttons = fixture.debugElement.queryAll(By.css('.dashboard-btn'));
    expect(buttons.length).toBe(4);
  });

  it('should display correct dashboard button labels', () => {
    const labels = fixture.debugElement
      .queryAll(By.css('.dashboard-btn span'))
      .map(el => el.nativeElement.textContent.trim());

    expect(labels).toEqual([
      'Admin Dashboard',
      'Employee Dashboard',
      'Payroll Processor Dashboard',
      'Manager Dashboard'
    ]);
  });
});

