import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FortnightCompilationsComponent } from './fortnight-compilations.component';

describe('FortnightCompilationsComponent', () => {
  let component: FortnightCompilationsComponent;
  let fixture: ComponentFixture<FortnightCompilationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FortnightCompilationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FortnightCompilationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
