import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestViewComponent } from './device-request-view.component';

describe('DeviceRequestViewComponent', () => {
  let component: DeviceRequestViewComponent;
  let fixture: ComponentFixture<DeviceRequestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
