import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentRequestEditComponent } from './shipment-request-edit.component';

describe('ShipmentRequestEditComponent', () => {
  let component: ShipmentRequestEditComponent;
  let fixture: ComponentFixture<ShipmentRequestEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipmentRequestEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipmentRequestEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
