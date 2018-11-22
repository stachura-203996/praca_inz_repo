import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {HttpErrorHandler} from './http-error-handler.service';
import {Observable} from 'rxjs';
import {catchError} from "rxjs/operators";
@Injectable()
export class HttpService {

  headers = new HttpHeaders({'token': '' });

  constructor(private http: HttpClient,
              private httpErrorHandler: HttpErrorHandler) {
  }

  get<T>(endpoint: string, httpOptions = {}): Observable<{}|T> {
    return this.http.get<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    }).pipe(catchError(err => this.httpErrorHandler.handleError(err)));
  }

  post<T>(endpoint: string, body, httpOptions = {}): Observable<{}|T> {
    return this.http.post<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    }).pipe(catchError(err => this.httpErrorHandler.handleError(err)));
  }

  put<T>(endpoint: string, body, httpOptions = {}): Observable<{}|T> {
    return this.http.put<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    }).pipe(catchError(err => this.httpErrorHandler.handleError(err)));
  }

  delete<T>(endpoint: string, httpOptions = {}) {
    return this.http.delete<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    }).pipe(catchError(err => this.httpErrorHandler.handleError(err)));
  }
}
