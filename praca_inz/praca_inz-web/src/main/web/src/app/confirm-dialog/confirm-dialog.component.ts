import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {Subject} from "rxjs";

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {
  title: string;
  message: string;
  decline: string;
  confirm: string;
  confirmed = new Subject<boolean>();

  constructor(public bsModalRef: BsModalRef) {
  }

  ngOnInit(): void {
  }

  public clickOk() {
    this.confirmed.next(true);
    this.bsModalRef.hide();
  }
}
