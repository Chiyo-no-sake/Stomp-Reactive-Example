import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {NgxLiveStompModule} from 'ngx-livestomp';
import {rxStompConfig} from "./stompConfig";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxLiveStompModule.forRoot(rxStompConfig)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
