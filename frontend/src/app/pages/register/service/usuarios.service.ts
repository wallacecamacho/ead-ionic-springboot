import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { ENDPOINT_API } from 'src/app/core/api.config';


@Injectable()
export class UsuariosService implements Resolve<any> {
    listObjects: any = [] as any;
    onObjectsChanged: BehaviorSubject<any>;

    private urnEndpoint = `${ENDPOINT_API}/${Constantes.USUARIOS_URL}`;

    /**
     * Constructor
     *
     * @param {HttpClient} _httpClient
     */
    constructor(
        private _httpClient: HttpClient
    ) {
        // Set the defaults
        this.onObjectsChanged = new BehaviorSubject({});
    }

    /**
     * Resolver
     *
     * @param {ActivatedRouteSnapshot} route
     * @param {RouterStateSnapshot} state
     * @returns {Observable<any> | Promise<any> | any}
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
        return new Promise((resolve, reject) => {

            Promise.all([
                this.getAll()
            ]).then(
                () => {
                    resolve();
                },
                reject
            );
        });
    }

    /**
     * Get products
     *
     * @returns {Promise<any>}
     */
    getAll(): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.get(this.urnEndpoint)
                .subscribe((response: any) => {
                    this.listObjects = response;
                    this.onObjectsChanged.next(this.listObjects);
                    resolve(response);
                }, reject);
        });
    }
}
