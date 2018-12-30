import {Directive, Input} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, Validator, ValidatorFn} from '@angular/forms';


export function passwordMatchValidator(pass: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    const res = pass !== control.value;
    return (res) ? {'noMatch': {value: control.value}} : null;
  };
}

@Directive({
  selector: '[appPasswordMatch]',
  providers: [{provide: NG_VALIDATORS, useExisting: PasswordMatchDirective, multi: true}]
})

export class PasswordMatchDirective implements Validator {
  @Input('appPasswordMatch') password: string;

  validate(control: AbstractControl): { [key: string]: any } {
    return this.password ? passwordMatchValidator(this.password)(control) : null;
  }

  constructor() {
  }
}
