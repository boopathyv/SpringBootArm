import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrelimsmaterialsComponent } from './prelimsmaterials.component';

describe('PrelimsmaterialsComponent', () => {
  let component: PrelimsmaterialsComponent;
  let fixture: ComponentFixture<PrelimsmaterialsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrelimsmaterialsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrelimsmaterialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
