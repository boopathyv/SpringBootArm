import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TnpscFaqComponent } from './tnpsc-faq.component';

describe('TnpscFaqComponent', () => {
  let component: TnpscFaqComponent;
  let fixture: ComponentFixture<TnpscFaqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TnpscFaqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TnpscFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
