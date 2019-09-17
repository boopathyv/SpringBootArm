import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutTnpscComponent } from './about-tnpsc.component';

describe('AboutTnpscComponent', () => {
  let component: AboutTnpscComponent;
  let fixture: ComponentFixture<AboutTnpscComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboutTnpscComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutTnpscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
