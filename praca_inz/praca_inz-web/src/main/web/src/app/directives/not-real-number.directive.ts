import {Directive} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidatorFn} from '@angular/forms';

export function notRealNumberValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    var regex = RegExp('^[1-9]\\d*$');
    const forbidden = regex.test(control.value);
    return forbidden ? null : {'realNumber': {value: control.value}};
  };
}

@Directive({
  selector: '[appNotRealNumber]',
  providers: [{provide: NG_VALIDATORS, useExisting: NotRealNumberDirective, multi: true}]
})
export class NotRealNumberDirective {

  validate(control: AbstractControl): { [key: string]: any } {
    return notRealNumberValidator()(control);
  }
}
