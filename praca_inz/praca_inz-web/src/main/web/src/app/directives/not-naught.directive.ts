import {Directive} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidatorFn} from '@angular/forms';

export function notNaughtValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    const negative = control.value == 0;
    return negative ? {'naught': {value: control.value}} : null;
  };
}

@Directive({
  selector: '[appNotNaught]',
  providers: [{provide: NG_VALIDATORS, useExisting: NotNaughtDirective, multi: true}]
})
export class NotNaughtDirective {

  validate(control: AbstractControl): { [key: string]: any } {
    return notNaughtValidator()(control);
  }
}
