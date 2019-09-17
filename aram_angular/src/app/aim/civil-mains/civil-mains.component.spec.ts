import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CivilMainsComponent } from './civil-mains.component';

describe('CivilMainsComponent', () => {
  let component: CivilMainsComponent;
  let fixture: ComponentFixture<CivilMainsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CivilMainsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CivilMainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
