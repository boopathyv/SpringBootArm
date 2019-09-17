import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainsmaterialsComponent } from './mainsmaterials.component';

describe('MainsmaterialsComponent', () => {
  let component: MainsmaterialsComponent;
  let fixture: ComponentFixture<MainsmaterialsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainsmaterialsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainsmaterialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
