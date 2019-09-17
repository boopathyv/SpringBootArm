import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpscSubmenuComponent } from './upsc-submenu.component';

describe('UpscSubmenuComponent', () => {
  let component: UpscSubmenuComponent;
  let fixture: ComponentFixture<UpscSubmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpscSubmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpscSubmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
