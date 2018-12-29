import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemMessageEditComponent } from './system-message-edit.component';

describe('SystemMessageEditComponent', () => {
  let component: SystemMessageEditComponent;
  let fixture: ComponentFixture<SystemMessageEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemMessageEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemMessageEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
