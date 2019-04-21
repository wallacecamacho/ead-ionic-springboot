import { Injectable } from '@angular/core';
import { HttpRequest, HttpInterceptor, HttpHandler } from '@angular/common/http';
import { of } from 'rxjs';
import { startWith } from 'rxjs/operators';
import { RequestCache } from '../cache/request-cache.service';
import { UtilsCache } from '../cache/utils-cache.service';


/**
 * If request is cachable (e.g., package search) and
 * response is in cache return the cached response as observable.
 * If has 'x-refresh' header that is true,
 * then also re-run the package search, using response from next(),
 * returning an observable that emits the cached response first.
 *
 * If not in cache or not cachable,
 * pass request through to next()
 */
@Injectable()
export class CachingInterceptor implements HttpInterceptor {
  constructor(private cache: RequestCache, private utilsCache: UtilsCache) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): any {
    // continue if not cachable.
    console.log('CachingInterceptor');
    if (!this.utilsCache.isCachable(req)) { return next.handle(req); }

    const cachedResponse = this.cache.get(req);
    // cache-then-refresh
    if (req.headers.get('x-refresh')) {
      const results$ = this.utilsCache.sendRequest(req, next, this.cache);
      return cachedResponse ?
        results$.pipe( startWith(cachedResponse) ) :
        results$;
    }
    // cache-or-fetch
    return cachedResponse ?
      of(cachedResponse) : this.utilsCache.sendRequest(req, next, this.cache);
  }
}

