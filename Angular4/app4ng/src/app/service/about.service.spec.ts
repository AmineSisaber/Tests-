import { TestBed, inject } from '@angular/core/testing';

import { AboutserviceService } from './aboutservice.service';

describe('AboutserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AboutserviceService]
    });
  });

  it('should be created', inject([AboutserviceService], (service: AboutserviceService) => {
    expect(service).toBeTruthy();
  }));
});
