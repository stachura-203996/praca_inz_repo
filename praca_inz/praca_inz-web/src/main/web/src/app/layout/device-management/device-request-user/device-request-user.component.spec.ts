import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestUserComponent } from './device-request-user.component';

describe('DeviceRequestUserComponent', () => {
  let component: DeviceRequestUserComponent;
  let fixture: ComponentFixture<DeviceRequestUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
