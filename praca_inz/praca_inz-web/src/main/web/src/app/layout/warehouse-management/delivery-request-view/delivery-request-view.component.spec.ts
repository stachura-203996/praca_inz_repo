import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryRequestViewComponent } from './delivery-request-view.component';

describe('DeliveryRequestViewComponent', () => {
  let component: DeliveryRequestViewComponent;
  let fixture: ComponentFixture<DeliveryRequestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryRequestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryRequestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
