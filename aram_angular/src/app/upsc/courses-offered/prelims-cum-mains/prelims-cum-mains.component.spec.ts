import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrelimsCumMainsComponent } from './prelims-cum-mains.component';

describe('PrelimsCumMainsComponent', () => {
  let component: PrelimsCumMainsComponent;
  let fixture: ComponentFixture<PrelimsCumMainsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrelimsCumMainsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrelimsCumMainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
