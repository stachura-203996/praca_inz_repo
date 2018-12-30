import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseDevicesComponent } from './warehouse-devices.component';

describe('WarehouseDevicesComponent', () => {
  let component: WarehouseDevicesComponent;
  let fixture: ComponentFixture<WarehouseDevicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehouseDevicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehouseDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
