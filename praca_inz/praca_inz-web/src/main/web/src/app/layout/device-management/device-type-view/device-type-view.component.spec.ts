import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceTypeViewComponent } from './device-type-view.component';

describe('DeviceTypeViewComponent', () => {
  let component: DeviceTypeViewComponent;
  let fixture: ComponentFixture<DeviceTypeViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceTypeViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceTypeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
