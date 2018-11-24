import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";

@Component({
  selector: 'app-profile-public',
  templateUrl: './profile-public.component.html',
  styleUrls: ['./profile-public.component.scss']
})
export class ProfilePublicComponent implements OnInit {

    currentUser: ProfileInfo;
  constructor() { }

  ngOnInit() {
  }

}
