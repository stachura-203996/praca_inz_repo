import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemMessageListComponent } from './system-message-list.component';

describe('SystemMessageListComponent', () => {
  let component: SystemMessageListComponent;
  let fixture: ComponentFixture<SystemMessageListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemMessageListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemMessageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
