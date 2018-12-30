import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemMessageAddComponent } from './system-message-add.component';

describe('SystemMessageAddComponent', () => {
  let component: SystemMessageAddComponent;
  let fixture: ComponentFixture<SystemMessageAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemMessageAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemMessageAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
