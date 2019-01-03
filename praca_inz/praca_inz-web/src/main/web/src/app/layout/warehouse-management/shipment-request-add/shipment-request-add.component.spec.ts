import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentRequestAddComponent } from './shipment-request-add.component';

describe('ShipmentRequestAddComponent', () => {
  let component: ShipmentRequestAddComponent;
  let fixture: ComponentFixture<ShipmentRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipmentRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipmentRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
