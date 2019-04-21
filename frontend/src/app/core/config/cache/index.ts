import { RequestCache, RequestCacheWithMap } from './request-cache.service';

export const cacheProviders = [
    { provide: RequestCache, useClass: RequestCacheWithMap }
];
