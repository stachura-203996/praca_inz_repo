import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestsComponent } from './device-requests.component';

describe('DeviceRequestsComponent', () => {
  let component: DeviceRequestsComponent;
  let fixture: ComponentFixture<DeviceRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
