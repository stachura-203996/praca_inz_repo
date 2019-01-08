import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportRequestAddComponent } from './report-request-add.component';

describe('ReportRequestAddComponent', () => {
  let component: ReportRequestAddComponent;
  let fixture: ComponentFixture<ReportRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportRequestAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
