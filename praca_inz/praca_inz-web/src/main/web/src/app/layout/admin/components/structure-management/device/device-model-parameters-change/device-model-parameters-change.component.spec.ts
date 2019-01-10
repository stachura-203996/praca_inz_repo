import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceModelParametersChangeComponent } from './device-model-parameters-change.component';

describe('DeviceModelParametersChangeComponent', () => {
  let component: DeviceModelParametersChangeComponent;
  let fixture: ComponentFixture<DeviceModelParametersChangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceModelParametersChangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceModelParametersChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
