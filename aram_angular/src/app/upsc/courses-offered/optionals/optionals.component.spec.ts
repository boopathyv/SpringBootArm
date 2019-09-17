import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionalsComponent } from './optionals.component';

describe('OptionalsComponent', () => {
  let component: OptionalsComponent;
  let fixture: ComponentFixture<OptionalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OptionalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OptionalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
