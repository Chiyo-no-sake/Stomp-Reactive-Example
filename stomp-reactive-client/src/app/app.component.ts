import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {NgxLiveStompService} from "ngx-livestomp";

interface Hero {
  name: string,
  rating: number
}

@Component({
  selector: 'app-root',
  template: `
    <p *ngFor="let hero of heroes | async">
      {{hero.name}}, rating: {{hero.rating}}
    </p>
    <br>
    Hero with id 1:
    <p>{{(heroWithID1 | async)?.name}}, rating: {{(heroWithID1 | async)?.rating}}</p>
    <br>
    Poster: {{poster?.text}}
    <p></p>
  `,
  styles: []
})
export class AppComponent implements OnInit {
  heroes?: Observable<Hero[]>;
  heroWithID1?: Observable<Hero>;
  poster?: {text: string};

  constructor(private restService: NgxLiveStompService) {
  }

  ngOnInit(): void {
    this.heroes = this.restService.liveList<Hero>('http://localhost:8080/heroes', '/topic/heroes/updates');
    this.heroWithID1 = this.restService.live<Hero>('http://localhost:8080/heroes/1', '/topic/heroes/1/updates');
    this.restService.live<{ text:string }>('http://localhost:8080/poster', '/topic/poster/updates').subscribe(poster => {
      console.log(poster);
      this.poster = poster
    })
  }

}
