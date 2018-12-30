import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehousesDeliveryComponent } from './warehouses-delivery.component';

describe('WarehousesDeliveryComponent', () => {
  let component: WarehousesDeliveryComponent;
  let fixture: ComponentFixture<WarehousesDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehousesDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehousesDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
