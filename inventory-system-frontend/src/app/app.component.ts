import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'inventory-system-frontend';
  content = '';

  constructor(private http: HttpClient) {}

  getResource(arg: string) {
    const endpoint = `/api/v1/${arg}`;
    this.http
      .get(endpoint)
      .pipe(take(1))
      .subscribe((result) => {
        this.content = JSON.stringify(result);
      });
  }
}
