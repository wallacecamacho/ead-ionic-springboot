import { Injectable } from '@angular/core';
import {
  HttpRequest,
} from '@angular/common/http';
import { Observable, of, Observer, ReplaySubject, Subject, timer } from 'rxjs';
import { delay, catchError, debounce, materialize, dematerialize } from 'rxjs/internal/operators';


import { HttpClient } from '@angular/common/http';
import { ENDPOINT_API } from '../../api.config';

import { INotification } from '../notifications/notification';
import { CONSTANTES } from '../constantes';
import { ILogin } from './model/login';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  redirectUrl: string;
  cachedRequests: Array<HttpRequest<any>> = [];

  private loginUrnEndpoint = `${ENDPOINT_API + `/login`}`;
  private observer: Observer<INotification>;
  public notification$: Observable<INotification>;

  public subject = new ReplaySubject<INotification>();
  // public subjectObs = new Subject.asObservable();

  constructor(
    private http: HttpClient
    ) {
    this.notification$ = new Observable(observer => this.observer = observer);
  }

  public login(login: ILogin): void {
    try {
      const loginResult = this.loginService(login).pipe(
        debounce(() => timer(1000)),
        // call materialize and dematerialize to ensure delay even if an
        // error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
        materialize(),
        delay(500),
        dematerialize(),
      )
      .subscribe(
        result => {
          this.setToken(environment.tokenApp, result.access_token);
          this.observer.next({ title: CONSTANTES.SUCCESS });
          this.subject.next({ title: CONSTANTES.SUCCESS });
        },
        err => {
          this.observer.next({ title: CONSTANTES.ERROR });
        });

    } catch (error) {
      console.log(error);
    }
  }

  private loginService(login: ILogin): Observable<any> {
    return this.http.post<ILogin>(this.loginUrnEndpoint, login)
      .pipe(
    //    catchError(this.handleError('adicionarCliente', login))
      );
  }

  public getToken(): string {
    return localStorage.getItem(environment.tokenApp) || sessionStorage.getItem(environment.tokenApp);
  }

  public setToken(key: string, token: string): void {
    sessionStorage.setItem(key, token);
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();
    if (token) {
      return true;
    }
    // return a boolean reflecting
    // whether or not the token is expired
    return false;
  }

  public collectFailedRequest(request): void {
    this.cachedRequests.push(request);
  }

  public retryFailedRequests(): void {
    // retry the requests. this method can
    // be called after the token is refreshed
  }

  public logout(): void {
    this.isLoggedIn = false;
  }
}
