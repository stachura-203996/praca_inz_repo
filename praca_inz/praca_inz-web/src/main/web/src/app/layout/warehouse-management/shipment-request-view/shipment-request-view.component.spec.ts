import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentRequestViewComponent } from './shipment-request-view.component';

describe('ShipmentRequestViewComponent', () => {
  let component: ShipmentRequestViewComponent;
  let fixture: ComponentFixture<ShipmentRequestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipmentRequestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipmentRequestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
