import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './Interfaces/login';
import { DataService } from './Services/data-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title:string = '';
  keys:number = this.title.length;;
  newDate = new Date();


  constructor(private dataService:DataService){
    
  }

  ngOnInit(){
    //Do during initialization
  }

  handleEvent($event:KeyboardEvent){
    let key:string = $event.key;
    console.log($event.key);
    this.keys = this.title.length;
  }

  
  
  
}
