import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestViewWarehouseComponent } from './device-request-view-warehouse.component';

describe('DeviceRequestViewWarehouseComponent', () => {
  let component: DeviceRequestViewWarehouseComponent;
  let fixture: ComponentFixture<DeviceRequestViewWarehouseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestViewWarehouseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestViewWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
