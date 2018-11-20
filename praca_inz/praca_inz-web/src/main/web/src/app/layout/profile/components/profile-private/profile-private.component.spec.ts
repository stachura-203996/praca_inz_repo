import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePrivateComponent } from './profile-private.component';

describe('ProfilePrivateComponent', () => {
  let component: ProfilePrivateComponent;
  let fixture: ComponentFixture<ProfilePrivateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilePrivateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilePrivateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
