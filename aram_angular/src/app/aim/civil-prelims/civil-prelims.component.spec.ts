import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CivilPrelimsComponent } from './civil-prelims.component';

describe('CivilPrelimsComponent', () => {
  let component: CivilPrelimsComponent;
  let fixture: ComponentFixture<CivilPrelimsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CivilPrelimsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CivilPrelimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
