import { TestBed, inject } from '@angular/core/testing';

import { AimcivilService } from './aimcivil.service';

describe('AimcivilService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AimcivilService]
    });
  });

  it('should be created', inject([AimcivilService], (service: AimcivilService) => {
    expect(service).toBeTruthy();
  }));
});
