import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubordinatesRequestsComponent } from './subordinates-requests.component';

describe('SubordinatesRequestsComponent', () => {
  let component: SubordinatesRequestsComponent;
  let fixture: ComponentFixture<SubordinatesRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubordinatesRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubordinatesRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
