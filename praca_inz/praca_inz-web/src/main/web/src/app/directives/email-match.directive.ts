import {Directive} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidatorFn} from '@angular/forms';

export function emailValidator(): ValidatorFn {
  let regex: RegExp;
  regex = new RegExp('[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?');
  return (control: AbstractControl): { [key: string]: any } => {
    const forbidden = regex.test(control.value);
    return forbidden ? null : {'invalidEmail': {value: control.value}};
  };
}

@Directive({
  selector: '[appEmailMatch]',
  providers: [{provide: NG_VALIDATORS, useExisting: EmailMatchDirective, multi: true}]
})
export class EmailMatchDirective {

  validate(control: AbstractControl): { [key: string]: any } {
    return emailValidator()(control);
  }
}
