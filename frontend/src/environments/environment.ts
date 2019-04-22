// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    hmr       : false,
    appName: '',
    apiUrl: 'http://localhost:8080',
    domainApp: 'imobli.com.br',
    cacheMaxAge: 3600,
    tokenApp: 'app.tkn.ath.gen',
    googleAnalytics: {
      domain: 'auto',
      trackingId: 'UA-49652641-2' // replace with your Tracking Id
    },
    siteKeyCaptcha: '6LcYy3kUAAAAADbAHg2BVjPgSEK0BpvWWqHgO2oi',
    secretKeyCaptcha: '6LcYy3kUAAAAADHFbhmA3V6zKP_LjVncbl2Vr-bw',
    apiKeyMap: 'AIzaSyCKsOnM_FIODIZ61-GPbioNySGBofoQz9g'
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
