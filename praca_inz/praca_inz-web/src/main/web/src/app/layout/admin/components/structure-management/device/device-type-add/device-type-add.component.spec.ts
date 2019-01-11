import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceTypeAddComponent } from './device-type-add.component';

describe('DeviceTypeAddComponent', () => {
  let component: DeviceTypeAddComponent;
  let fixture: ComponentFixture<DeviceTypeAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceTypeAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceTypeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
