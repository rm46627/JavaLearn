import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  url = "http://localhost:8080/admin/users";
  observableUsers: Observable<User[]>;
  users: User[] = [];


  constructor(private http: HttpClient) {
    this.observableUsers = this.getUsersWithObservable()
    this.observableUsers.subscribe(
      users => this.users = users
    )}

  ngOnInit(): void { }

  getUsersWithObservable(): Observable<User[]> {
      return this.http.get(this.url).pipe(
          map(this.extractData)
      )
  }

  private extractData(res: any) {
      let body = res;
      return body;
  }
}
