import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestEditComponent } from './device-request-edit.component';

describe('DeviceRequestEditComponent', () => {
  let component: DeviceRequestEditComponent;
  let fixture: ComponentFixture<DeviceRequestEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
