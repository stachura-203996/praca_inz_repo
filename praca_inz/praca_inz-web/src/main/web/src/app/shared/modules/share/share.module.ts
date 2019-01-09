import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EmailMatchDirective} from "../../../directives/email-match.directive";
import {NotNaughtDirective} from "../../../directives/not-naught.directive";
import {NotNegativeDirective} from "../../../directives/not-negative.directive";
import {NotRealNumberDirective} from "../../../directives/not-real-number.directive";
import {PasswordMatchDirective} from "../../../directives/password-match.directive";

@NgModule({
    declarations: [
        EmailMatchDirective,
        NotNaughtDirective,
        NotNegativeDirective,
        NotRealNumberDirective,
        PasswordMatchDirective
    ],
    imports: [
        CommonModule
    ],
    exports:[
        EmailMatchDirective,
        NotNaughtDirective,
        NotNegativeDirective,
        NotRealNumberDirective,
        PasswordMatchDirective
    ]
})
export class ShareModule {
}
