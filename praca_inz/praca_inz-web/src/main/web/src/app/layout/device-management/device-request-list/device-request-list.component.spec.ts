import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestListComponent } from './device-request-list.component';

describe('DeviceRequestListComponent', () => {
  let component: DeviceRequestListComponent;
  let fixture: ComponentFixture<DeviceRequestListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
