import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferUserComponent } from './transfer-user.component';

describe('TransferUserComponent', () => {
  let component: TransferUserComponent;
  let fixture: ComponentFixture<TransferUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
