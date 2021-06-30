import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { Person } from './person';

@Injectable({
  providedIn: 'root',
})
export class SearchService {

  constructor(
    private http: HttpClient,
    ) { }
  newSearch = new Subject<string>();

  resultsFound = 0;

  searchData(wholename: string): Observable<Person[]>  {

    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
      }),
    };
    return this.http.get(
      'http://localhost:9000/watchlist/',
      {
        params: new HttpParams().set('wholename', wholename),
        headers: new HttpHeaders({
          'Access-Control-Allow-Origin': '*',
        }),
      },
      )
      .pipe(map(
        responseData => {
            const peopleArray: Person[] = [];
            // tslint:disable-next-line:no-console
            console.log(responseData['searchHits'][0]['content']);
            for (const person of Object.keys(responseData['searchHits'])) {
              peopleArray.push({ ... responseData['searchHits'][person]['content'],
                score: responseData['searchHits'][person]['score'] }); // gosh!
            }
            this.resultsFound = responseData['totalHits'];
            return peopleArray;
        }));
  }

  // getById(id: string) {
  //   return
  //   this.http.get(
  //     "localhost:8080/person/",
  //     {params: {id: id}}
  //     );
  //     // just go to this position and get the results...
  // }

}
