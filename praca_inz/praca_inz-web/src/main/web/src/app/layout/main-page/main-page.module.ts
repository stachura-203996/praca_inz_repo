import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbCarouselModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { MainPageRoutingModule } from './main-page-routing.module';
import { MainPageComponent } from './main-page.component';
import { PageHeaderModule } from './../../shared';
import {
    TimelineComponent,
    NotificationComponent,
    ChatComponent
} from './components';
import { StatModule } from '../../shared';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        NgbAlertModule.forRoot(),
        MainPageRoutingModule,
        StatModule,
        TranslateModule,
        PageHeaderModule
    ],
    declarations: [
        MainPageComponent,
        TimelineComponent,
        NotificationComponent,
        ChatComponent
    ]
})
export class MainPageModule {}
