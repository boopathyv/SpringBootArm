import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainsQaComponent } from './mains-qa.component';

describe('MainsQaComponent', () => {
  let component: MainsQaComponent;
  let fixture: ComponentFixture<MainsQaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainsQaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainsQaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
