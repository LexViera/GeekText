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

  @Input() loginDetails:Login;

  @Output() loginEvent: EventEmitter<Login>;

  constructor(private dataService:DataService) {
    this.loginDetails = {} as Login;
    this.loginEvent = new EventEmitter<Login>();
    this.response = {} as Message;
    this.response.message = "Please enter your log in details";
   }
  
  ngOnInit(): void {

  }

  sendLogin(){
    console.log(this.loginDetails);
    this.dataService.postRequest("https://geek-text-g15.herokuapp.com/login",this.loginDetails)
    .subscribe(response => {
      this.response = response;
      console.log(response);
    });
  }

}
