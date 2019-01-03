import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferRequestEditComponent } from './transfer-request-edit.component';

describe('TransferRequestEditComponent', () => {
  let component: TransferRequestEditComponent;
  let fixture: ComponentFixture<TransferRequestEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferRequestEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferRequestEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
