import {Injectable, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {RxStompService} from "@stomp/ng2-stompjs";
import {map} from "rxjs/operators";
import {ChangeEventMessage} from "./dto";

export type Hero = {
  name: string,
  rating: number
}

@Injectable({
  providedIn: 'root'
})
export class LiveWebSocketService implements OnInit{

  constructor(private rxStompService: RxStompService) {}

  ngOnInit(): void {
  }

  listenChanges<Type>(path: string): Observable<ChangeEventMessage<Type>> {
    return this.rxStompService
      .watch(path).pipe(map(message => {
        return JSON.parse(message.body) as ChangeEventMessage<Type>;
      }));
  }

}
