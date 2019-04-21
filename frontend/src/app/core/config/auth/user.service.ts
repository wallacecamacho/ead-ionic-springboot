import { Injectable } from '@angular/core';
import {
  HttpRequest, HttpParams,
} from '@angular/common/http';
import { Observable, of, Observer, ReplaySubject, Subject, timer, BehaviorSubject } from 'rxjs';
import { delay, catchError, debounce, materialize, dematerialize, map } from 'rxjs/internal/operators';


import { HttpClient } from '@angular/common/http';
import { ENDPOINT_API } from '../../api.config';
import { IUser } from './model/user';
import { CONSTANTES } from '../constantes';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  isLoggedIn = false;
  redirectUrl: string;
  cachedRequests: Array<HttpRequest<any>> = [];

  private userUrnEndpoint = `${ENDPOINT_API}/${CONSTANTES.USER_URL}`;

  constructor(
    private http: HttpClient,
    ) {
    }

  public searchUserService(email: string): Observable<IUser> {

    // const params = new HttpParams({ fromObject: { email: email } });

    return this.http.get<IUser>(`${this.userUrnEndpoint}/${email}`).pipe(
      //  catchError(this.handleError('UserService', params))
    );
  }
}
