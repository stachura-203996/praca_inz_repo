import { Component, OnInit } from '@angular/core';
import {UserEdit} from "../../../models/user-edit";
import {ActivatedRoute, Router} from "@angular/router";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../shared/services/message.service";
import {StructureListElement} from "../../../models/structure-elements";
import {ProfileService} from "../profile.service";
import {ProfileEdit} from "../../../models/profile-edit";
import {Configuration} from "../../../app.constants";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss']
})
export class ProfileEditComponent implements OnInit {


    userEditElement: ProfileEdit;

    offices = new Map<string, number>();

    languages:string[]=['PL','ENG'];

    constructor(
        private route: ActivatedRoute,
        private profileService: ProfileService,
        private officeService: OfficeService,
        private translate: TranslateService,
        private messageService:MessageService,
        private configuration: Configuration,
        private router: Router) {}

    ngOnInit() {
        this.getOffices();
        this.getProfileEdit();
    }


    getProfileEdit(){
        const id = this.route.snapshot.paramMap.get('id');
        this.profileService.getUserProfileEdit().subscribe(x=>this.userEditElement=x);
    }

    getOffices() {
        this.officeService.getAll().subscribe((response: StructureListElement[]) => {
            this.offices = response.reduce(function (officeMap, office) {
                if (office.id) {
                    officeMap.set(office.name, office.id)
                }
                return officeMap;
            }, this.offices);
        });

    }


    profileUpdate() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('profile.edit').subscribe(x => entity = x);
        this.translate.get('confirm.edit').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.userEditElement.officeId = this.offices.get(this.userEditElement.officeName);
                    this.profileService.updateAccountByUser(this.userEditElement)
                        .subscribe(resp => {
                            this.router.navigateByUrl('/profile');
                            this.translate.get('success.profile.edit').subscribe(x=>{
                                this.messageService.success(x)
                            })
                        }, error => {
                            if (error === this.configuration.OPTIMISTIC_LOCK) {
                                this.translate.get('optimistic.lock').subscribe(x => {
                                    this.messageService.error(x);
                                })
                            } else  if (error === this.configuration.ERROR_USERNAME_TAKEN) {
                                this.translate.get('username.taken.error').subscribe(x => {
                                    this.messageService.error(x);
                                })
                            } else if (error === this.configuration.ERROR_EMAIL_TAKEN) {
                                this.translate.get('email.taken.error').subscribe(x => {
                                    this.messageService.error(x);
                                })
                            } else {
                                this.translate.get('unknown.error').subscribe(x => {
                                    this.messageService.error(x);
                                })
                            }

                        });
                }
            });
    }

    clear() {
        this.userEditElement = new UserEdit();
    }

}
