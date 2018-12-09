import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class HttpService {

  headers = new HttpHeaders({'authorization': 'Bearer '+this.cookieService.get('access_token')});

  constructor(private http: HttpClient, private cookieService:CookieService) {
  }

  get<T>(endpoint: string, httpOptions = {}): Observable<T> {
    return this.http.get<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    })
  }

  post<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    return this.http.post<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    })
  }

  put<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    return this.http.put<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    })
  }

  delete<T>(endpoint: string, httpOptions = {}) {
    return this.http.delete<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    })
  }
}
