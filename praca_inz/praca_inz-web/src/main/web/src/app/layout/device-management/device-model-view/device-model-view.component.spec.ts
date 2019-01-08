import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceModelViewComponent } from './device-model-view.component';

describe('DeviceModelViewComponent', () => {
  let component: DeviceModelViewComponent;
  let fixture: ComponentFixture<DeviceModelViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceModelViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceModelViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
