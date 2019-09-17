import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreshersMainsComponent } from './freshers-mains.component';

describe('FreshersMainsComponent', () => {
  let component: FreshersMainsComponent;
  let fixture: ComponentFixture<FreshersMainsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreshersMainsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreshersMainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
