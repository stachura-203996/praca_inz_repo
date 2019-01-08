import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceModelEditComponent } from './device-model-edit.component';

describe('DeviceModelEditComponent', () => {
  let component: DeviceModelEditComponent;
  let fixture: ComponentFixture<DeviceModelEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceModelEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceModelEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
