import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { BehaviorSubject, Observable, AsyncSubject } from 'rxjs';
import { INota } from '../model/nota.model';
import { CONSTANTES } from 'src/app/core/config/constantes';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { INotification } from 'src/app/core/config/notifications/notification';

@Injectable()
export class NotaService implements Resolve<any> {
    routeParams: any;
    nota: any;
    onObjectChanged: BehaviorSubject<any>;

    public subjectSuccess = new AsyncSubject<INotification>();
    public subjectError = new AsyncSubject<INotification>();

    private urnEndpoint = `${ENDPOINT_API}/${CONSTANTES.NOTAS_URL}/`;



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
                this.getProduct()
            ]).then(
                () => {
                    resolve();
                },
                reject
            );
        });
    }

    /**
     * Get product
     *
     * @returns {Promise<any>}
     */
    getProduct(): Promise<any> {
        return new Promise((resolve, reject) => {
            if ( this.routeParams.id === 'new' ) {
                this.onObjectChanged.next(false);
                resolve(false);
            } else {
                this._httpClient.get(this.urnEndpoint + this.routeParams.id)
                    .subscribe((response: any) => {
                        this.nota = response;
                        this.onObjectChanged.next(this.nota);
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
    save(object: INota): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.put(this.urnEndpoint, object)
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
    add(object: INota): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.post(this.urnEndpoint, object)
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
    delete(object): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.delete(this.urnEndpoint + object.idNota)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }
}
