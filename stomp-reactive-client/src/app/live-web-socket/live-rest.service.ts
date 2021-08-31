import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LiveWebSocketService} from "./live-web-socket.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LiveRestService {

  constructor(private httpClient: HttpClient, private webSocket: LiveWebSocketService) {
  }

  live<Type>(restEndpoint: string, updatesPath: string): Observable<Type> {
    return new Observable<Type>(subscriber => {
      this.httpClient.get<Type>(restEndpoint).subscribe((item) => subscriber.next(item));
      this.webSocket.listenChanges<Type>(updatesPath).subscribe(update => {
        if(update.changeType === "DELETED") {
          subscriber.next(undefined);
          subscriber.complete()
        }
        subscriber.next(update.subject)
      });
    })
  }

  liveList<Type>(restEndpoint: string, updatesPath: string): Observable<(Type & { id: any })[]> {
    type withId = Type & { id: any };

    let currentList: withId[] = [];

    return new Observable<withId[]>(subscriber => {
      this.httpClient.get<withId[]>(restEndpoint).subscribe((list) => {
        currentList = list;
        subscriber.next(list);
      });

      this.webSocket.listenChanges<withId>(updatesPath).subscribe(update => {
        switch (update.changeType) {
          case "CREATED":
            currentList = [...currentList, update.subject];
            break;
          case "UPDATED":
            const index = currentList.findIndex((item) => item.id === update.subject.id);
            currentList[index] = update.subject;
            break;
          case "DELETED":
            currentList = currentList.filter(item => item.id !== update.subject.id)
            break;
        }

        subscriber.next(currentList);
      })
    })
  }
}
