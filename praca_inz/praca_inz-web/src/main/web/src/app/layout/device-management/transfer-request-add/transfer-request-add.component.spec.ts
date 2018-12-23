import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferRequestAddComponent } from './transfer-request-add.component';

describe('TransferRequestAddComponent', () => {
  let component: TransferRequestAddComponent;
  let fixture: ComponentFixture<TransferRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
