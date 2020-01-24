import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationRequestAddComponent } from './confirmation-request-add.component';

describe('ConfirmationRequestAddComponent', () => {
  let component: ConfirmationRequestAddComponent;
  let fixture: ComponentFixture<ConfirmationRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
