import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {InjectableRxStompConfig, RxStompService, rxStompServiceFactory} from "@stomp/ng2-stompjs";
import {rxStompConfig} from "./live-web-socket/rx-stomp.config";
import {HttpClientModule} from "@angular/common/http";
import {LiveWebSocketModule} from "./live-web-socket/live-web-socket.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    LiveWebSocketModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
