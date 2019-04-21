import { Injectable } from '@angular/core';
import {
    HttpEvent, HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpRequest,
    HttpInterceptor, HttpHandler
  } from '@angular/common/http';

import { Observable } from 'rxjs';
import { startWith, tap } from 'rxjs/operators';
import { RequestCache } from './request-cache.service';

@Injectable()
export class UtilsCache {

    searchUrl = 'https://npmsearch.com/query';

    public constructor () {
    }

    public createHttpOptions(packageName: string, refresh = false) {
        const params = new HttpParams({ fromObject: { q: packageName } });
        const headerMap = refresh ? {'x-refresh': 'true'} : {};
        const headers = new HttpHeaders(headerMap) ;
        return { headers, params };
    }


    /** Is this request cachable? */
    public isCachable(req: HttpRequest<any>) {

      console.log('UtilsCache');

    // Only GET requests are cachable
    return req.method === 'GET' &&
      // Only npm package search is cachable in this app
      -1 < req.url.indexOf(this.searchUrl);
  }

    /**
     * Get server response observable by sending request to `next()`.
     * Will add the response to the cache on the way out.
     */
    public sendRequest(
    req: HttpRequest<any>,
    next: HttpHandler,
    cache: RequestCache): Observable<HttpEvent<any>> {

    // No headers allowed in npm search request
    const noHeaderReq = req.clone({ headers: new HttpHeaders() });

    return next.handle(noHeaderReq).pipe(
      tap(event => {
        // There may be other events besides the response.
        if (event instanceof HttpResponse) {
          cache.put(req, event); // Update the cache.
        }
      })
    );
  }
}
