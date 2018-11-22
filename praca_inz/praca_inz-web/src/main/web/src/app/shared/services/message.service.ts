import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {BsModalService} from 'ngx-bootstrap';
import {Observable} from "rxjs";
import {ConfirmDialogComponent} from "../../confirm-dialog/confirm-dialog.component";

@Injectable()
export class MessageService {

  constructor(private toastrService: ToastrService,
              private modalService: BsModalService) {
  }

  confirm(title?: string, message?: string, confirm?: string, decline?: string): Observable<boolean> {
    const initialState = {
      title: title,
      message: message,
      confirm: confirm,
      decline: decline
    };
    const bsModalRef = this.modalService.show(ConfirmDialogComponent,
      {animated: true, keyboard: true, backdrop: true, ignoreBackdropClick: true, initialState: initialState});

    return bsModalRef.content.confirmed;
  }

  success(message: string, title?: string) {
    this.toastrService.success(message, title);
  }

  info(message: string, title?: string) {
    this.toastrService.info(message, title);
  }

  warning(message: string, title?: string) {
    this.toastrService.warning(message, title);
  }

  error(message: string, title?: string) {
    this.toastrService.error(message, title);
  }
}
