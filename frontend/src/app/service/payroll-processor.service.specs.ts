import { TestBed } from '@angular/core/testing';

import { PayrollProcessorService } from './payroll-processor.service';

describe('PayrollProcessorService', () => {
  let service: PayrollProcessorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PayrollProcessorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});