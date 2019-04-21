import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { authProvider } from './auth/auth.provider';
import { AuthModule } from './auth/auth.module';
import { UtilsCache } from './cache/utils-cache.service';
import { cacheProviders } from './cache/index';
import { httpInterceptorAppProviders } from './interceptors/index';
import { MessageLoggerService } from './logger/message-logger.service';
import { configAppProviders } from './app/index';

import { AuthGuardService } from './auth/guard/auth-guard.service';
import { MessageLoggerModule } from './logger/message-logger.module';
import { PageNotFoundComponent } from './pages/not-found.component';
import { MessagesLoggerComponent } from './logger/messages-logger.component';

import { AuthService } from './auth/auth.service';

@NgModule({
  imports: [
    CommonModule,
    AuthModule,
    MessageLoggerModule,
  ],
  exports: [
    MessageLoggerModule,
    AuthModule
  ],
  declarations: [
    PageNotFoundComponent,
    MessagesLoggerComponent,
  ],
  entryComponents: [
  ],
  providers: [
    configAppProviders,
    MessageLoggerService,
    AuthService,
    httpInterceptorAppProviders,
    cacheProviders,
    UtilsCache,
    authProvider,
    AuthGuardService,

  ]
})
export class ConfigModule { }
