import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryRequestListComponent } from './delivery-request-list.component';

describe('DeliveryRequestListComponent', () => {
  let component: DeliveryRequestListComponent;
  let fixture: ComponentFixture<DeliveryRequestListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryRequestListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
