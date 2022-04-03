import { HttpHeaders } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Login } from 'src/app/Interfaces/login';
import { Message } from 'src/app/Interfaces/message';
import { DataService } from 'src/app/Services/data-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  response;
  
  private cookie:any = {};

  @Input() loginDetails: Login;

  @Output() loginEvent: EventEmitter<Login>;

  constructor(private dataService: DataService) {
    this.loginDetails = {} as Login;
    this.loginEvent = new EventEmitter<Login>();
    this.response = {} as Message;
    this.response.message = "Please enter your log in details";
  }

  ngOnInit(): void {}

  sendLogin() {
    let options = {
      observe: 'response',
      headers:{
        "withCredentials":true
      }, 
    };
    
    this.dataService.postRequest("https://geek-text-g15.herokuapp.com/login", this.loginDetails, options)
      .subscribe(response => {
        this.response = response.body;
        //console.log(response);
        console.log(response);
        console.log(response.headers)
      });
  }
}
