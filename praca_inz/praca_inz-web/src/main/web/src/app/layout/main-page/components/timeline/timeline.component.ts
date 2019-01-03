import { Component, OnInit } from '@angular/core';
import {SystemMessageService} from "../../system-message.service";
import {SystemMessageListElement} from "../../../../models/system-message-list-element";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

    systemMessages:SystemMessageListElement[];
    site:boolean=true;

  constructor(private systemMessageService:SystemMessageService) { }

  ngOnInit() {
      this.getSystemMessages();
  }

  changeSite(){
      if(this.site){
          this.site=false;
      } else{
          this.site=true;
      }
  }
  getSystemMessages(){
      this.systemMessageService.getAllMessages().subscribe(companyListElement=> {this.systemMessages=companyListElement});
  }

}
