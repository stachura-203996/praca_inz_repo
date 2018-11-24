import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "./models/profile-info";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    currentUser: ProfileInfo;

  constructor() { }

  ngOnInit() {
  }

}
