import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {ConfirmationUserComponent} from "./confirmation-user.component";




describe('ReportComponent', () => {
  let component: ConfirmationUserComponent;
  let fixture: ComponentFixture<ConfirmationUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
