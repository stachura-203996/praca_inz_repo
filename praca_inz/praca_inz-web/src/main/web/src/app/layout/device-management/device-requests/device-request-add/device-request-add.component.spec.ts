import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceRequestAddComponent } from './device-request-add.component';

describe('DeviceRequestAddComponent', () => {
  let component: DeviceRequestAddComponent;
  let fixture: ComponentFixture<DeviceRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
