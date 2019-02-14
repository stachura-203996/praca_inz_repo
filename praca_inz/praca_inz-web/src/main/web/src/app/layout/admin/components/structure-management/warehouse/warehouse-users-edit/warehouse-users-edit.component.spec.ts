import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseUsersEditComponent } from './warehouse-users-edit.component';

describe('WarehouseUsersEditComponent', () => {
  let component: WarehouseUsersEditComponent;
  let fixture: ComponentFixture<WarehouseUsersEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehouseUsersEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehouseUsersEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
