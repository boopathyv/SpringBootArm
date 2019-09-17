import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TnpscmaterialsComponent } from './tnpscmaterials.component';

describe('TnpscmaterialsComponent', () => {
  let component: TnpscmaterialsComponent;
  let fixture: ComponentFixture<TnpscmaterialsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TnpscmaterialsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TnpscmaterialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
