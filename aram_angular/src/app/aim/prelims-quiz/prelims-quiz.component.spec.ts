import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrelimsQuizComponent } from './prelims-quiz.component';

describe('PrelimsQuizComponent', () => {
  let component: PrelimsQuizComponent;
  let fixture: ComponentFixture<PrelimsQuizComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrelimsQuizComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrelimsQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
