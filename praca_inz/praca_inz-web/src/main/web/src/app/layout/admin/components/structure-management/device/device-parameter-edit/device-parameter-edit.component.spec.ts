import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceParameterEditComponent } from './device-parameter-edit.component';

describe('DeviceParameterEditComponent', () => {
  let component: DeviceParameterEditComponent;
  let fixture: ComponentFixture<DeviceParameterEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceParameterEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceParameterEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
