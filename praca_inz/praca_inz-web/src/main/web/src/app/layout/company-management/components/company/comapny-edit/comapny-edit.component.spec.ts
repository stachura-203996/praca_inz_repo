import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComapnyEditComponent } from './comapny-edit.component';

describe('ComapnyEditComponent', () => {
  let component: ComapnyEditComponent;
  let fixture: ComponentFixture<ComapnyEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComapnyEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComapnyEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
