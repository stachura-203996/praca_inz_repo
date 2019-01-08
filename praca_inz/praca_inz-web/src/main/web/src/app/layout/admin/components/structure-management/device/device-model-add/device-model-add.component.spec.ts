import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceModelAddComponent } from './device-model-add.component';

describe('DeviceModelAddComponent', () => {
  let component: DeviceModelAddComponent;
  let fixture: ComponentFixture<DeviceModelAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceModelAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceModelAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
