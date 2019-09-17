import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrashCourseComponent } from './crash-course.component';

describe('CrashCourseComponent', () => {
  let component: CrashCourseComponent;
  let fixture: ComponentFixture<CrashCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrashCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrashCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
