import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutUpscComponent } from './about-upsc.component';

describe('AboutUpscComponent', () => {
  let component: AboutUpscComponent;
  let fixture: ComponentFixture<AboutUpscComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboutUpscComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutUpscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
