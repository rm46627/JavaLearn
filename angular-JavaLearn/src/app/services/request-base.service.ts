import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from "../models/user";
import { AuthService } from "./auth.service";


export abstract class RequestBaseService {

  protected currentUser: User = {} as User

  protected constructor(protected authService: AuthService, protected httpClient: HttpClient) { 
    this.authService.currentUser.subscribe( data => {
      this.currentUser = data
    })
  }

  get getHeaders(): HttpHeaders {
    return new HttpHeaders({
      authorization: 'Bearer ' + this.currentUser?.accessToken,
      "Content-Type": "application/json; charset=UTF-8" 
    })
  }
}
