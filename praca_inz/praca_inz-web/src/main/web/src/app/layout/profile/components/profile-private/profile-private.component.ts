import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";

@Component({
  selector: 'app-profile-private',
  templateUrl: './profile-private.component.html',
  styleUrls: ['./profile-private.component.scss']
})
export class ProfilePrivateComponent implements OnInit {
    currentUser: ProfileInfo;
  constructor() { }

  ngOnInit() {
  }

}
