import {CommonModule, HashLocationStrategy, Location, LocationStrategy} from '@angular/common';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthGuard} from './shared';
import {ConfirmDialogComponent} from "./confirm-dialog/confirm-dialog.component";
import {LoginService} from "./login/login.service";
import {CookieService} from "ngx-cookie-service";
import {UserService} from "./layout/user-management/user.service";
import {HttpService} from "./shared/services/http.service";
import {Configuration} from "./app.constants";
import {ProfileService} from "./layout/profile/profile.service";
import {MessageService} from "./shared/services/message.service";
import {I18nService} from "./shared/services/i18n/i18n.service";
import {ToastrModule} from "ngx-toastr";


export const createTranslateLoader = (http: HttpClient) => {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        AppRoutingModule,
        ToastrModule.forRoot({
            positionClass: 'toast-top-center',
            maxOpened: 2,
            autoDismiss: true
        }),
    ],
    declarations: [
        AppComponent,
        ConfirmDialogComponent
    ],
    providers: [
        AuthGuard,
        LoginService,
        CookieService,
        UserService,
        HttpService,
        Configuration,
        ProfileService,
        MessageService,
        I18nService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
