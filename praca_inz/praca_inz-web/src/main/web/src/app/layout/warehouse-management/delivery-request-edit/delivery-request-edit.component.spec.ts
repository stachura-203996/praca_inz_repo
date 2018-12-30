import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryRequestEditComponent } from './delivery-request-edit.component';

describe('DeliveryRequestEditComponent', () => {
  let component: DeliveryRequestEditComponent;
  let fixture: ComponentFixture<DeliveryRequestEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryRequestEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryRequestEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
