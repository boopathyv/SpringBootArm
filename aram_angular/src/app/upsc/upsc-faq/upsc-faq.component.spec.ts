import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpscFaqComponent } from './upsc-faq.component';

describe('UpscFaqComponent', () => {
  let component: UpscFaqComponent;
  let fixture: ComponentFixture<UpscFaqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpscFaqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpscFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
