import {Directive} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidatorFn} from '@angular/forms';

export function notNegativeValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    const negative = control.value < 0;
    return negative ? {'negative': {value: control.value}} : null;
  };
}

@Directive({
  selector: '[appNotNegative]',
  providers: [{provide: NG_VALIDATORS, useExisting: NotNegativeDirective, multi: true}]
})
export class NotNegativeDirective {

  validate(control: AbstractControl): { [key: string]: any } {
    return notNegativeValidator()(control);
  }
}
