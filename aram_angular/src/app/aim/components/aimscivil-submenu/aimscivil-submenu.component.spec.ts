import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AimscivilSubmenuComponent } from './aimscivil-submenu.component';

describe('AimscivilSubmenuComponent', () => {
  let component: AimscivilSubmenuComponent;
  let fixture: ComponentFixture<AimscivilSubmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AimscivilSubmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AimscivilSubmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
