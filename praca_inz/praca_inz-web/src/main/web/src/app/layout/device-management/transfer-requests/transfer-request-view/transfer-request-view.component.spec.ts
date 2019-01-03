import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferRequestViewComponent } from './transfer-request-view.component';

describe('TransferRequestViewComponent', () => {
  let component: TransferRequestViewComponent;
  let fixture: ComponentFixture<TransferRequestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferRequestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferRequestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
