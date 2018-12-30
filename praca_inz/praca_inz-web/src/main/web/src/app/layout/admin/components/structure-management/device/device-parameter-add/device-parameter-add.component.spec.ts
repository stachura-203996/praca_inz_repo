import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceParameterAddComponent } from './device-parameter-add.component';

describe('DeviceParameterAddComponent', () => {
  let component: DeviceParameterAddComponent;
  let fixture: ComponentFixture<DeviceParameterAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceParameterAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceParameterAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
