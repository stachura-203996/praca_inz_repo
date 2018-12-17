import { async, ComponentFixture, TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { TranslateModule } from '@ngx-translate/core'
import { MainPageComponent } from './main-page.component'
import { MainPageModule } from './main-page.module'

describe('MainPageComponent', () => {
  let component: MainPageComponent
  let fixture: ComponentFixture<MainPageComponent>

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MainPageModule,
        RouterTestingModule,
        BrowserAnimationsModule,
          TranslateModule.forRoot()
       ]
    })
    .compileComponents()
  }))

  beforeEach(() => {
    fixture = TestBed.createComponent(MainPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
