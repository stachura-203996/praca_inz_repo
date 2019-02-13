import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalTransferListComponent } from './external-transfer-list.component';

describe('ExternalTransferListComponent', () => {
  let component: ExternalTransferListComponent;
  let fixture: ComponentFixture<ExternalTransferListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExternalTransferListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExternalTransferListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
