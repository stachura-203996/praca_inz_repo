import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryRequestAddComponent } from './delivery-request-add.component';

describe('DeliveryRequestAddComponent', () => {
  let component: DeliveryRequestAddComponent;
  let fixture: ComponentFixture<DeliveryRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
