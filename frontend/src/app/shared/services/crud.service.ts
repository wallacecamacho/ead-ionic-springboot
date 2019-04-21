import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { BehaviorSubject, Observable, AsyncSubject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';

@Injectable()
export class CrudService implements Resolve<any> {
    routeParams: any;
    list: any;
    onObjectChanged: BehaviorSubject<any>;

    public subjectSuccess = new AsyncSubject<INotification>();
    public subjectError = new AsyncSubject<INotification>();

    /**
     * Constructor
     *
     * @param {HttpClient} _httpClient
     */
    constructor(
        private _httpClient: HttpClient
    ) {
        // Set the defaults
        this.onObjectChanged = new BehaviorSubject({});
    }

    /**
     * Resolver
     *
     * @param {ActivatedRouteSnapshot} route
     * @param {RouterStateSnapshot} state
     * @returns {Observable<any> | Promise<any> | any}
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
        this.routeParams = route.params;

        return new Promise((resolve, reject) => {

            Promise.all([
                this.get(null, this.routeParams)
            ]).then(
                () => {
                    resolve();
                },
                reject
            );
        });
    }

    /**
     * Get All
     *
     * @returns {Promise<any>}
     */
    getAll(url: string): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.get(url)
                .subscribe((response: any) => {
                    this.list = response;
                    resolve(response);
                }, reject);
        });
    }

    /**
     * Get
     *
     * @returns {Promise<any>}
     */
    get(url: string, param: any): Promise<any> {
        return new Promise((resolve, reject) => {
            if ( param === 'new' ) {
                this.onObjectChanged.next(false);
                resolve(false);
            } else {
                this._httpClient.get(`${url}/${param}`)
                    .subscribe((response: any) => {
                        this.list = response;
                        resolve(response);
                    }, reject);
            }
        });
    }

    /**
     * Save object
     *
     * @param corretor
     * @returns {Promise<any>}
     */
    save(url: string, object: any): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.put(url, object)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }

    /**
     * Add object
     *
     * @param corretor
     * @returns {Promise<any>}
     */
    add(url: string, object: any): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.post(url, object)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }

    /**
     * Delete object
     *
     * @param corretor
     * @returns {Promise<any>}
     */
    delete(url: string, object: any): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.delete(url + object.idnota)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }
}
