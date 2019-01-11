import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class I18nService {

  private messages = {};

  constructor(private http: HttpClient) {
    this.refresh();
  }

  refresh(): void {
    this.http.get('./assets/i18n/messages-' + navigator.language.substring(0, 2) + '.json')
      .subscribe(data => {
          this.messages = data;
        }, () => {
          this.http.get('./assets/i18n/messages-pl.json').subscribe(data => {
              this.messages = data;
            }, (err: HttpErrorResponse) => {
              this.messages = {};
              console.log(err.message);
            }
          );
        }
      );
  }

  getMessage(key: string): string {
    const message = this.messages[key];
    return message === undefined ? key : message;
  }
}
