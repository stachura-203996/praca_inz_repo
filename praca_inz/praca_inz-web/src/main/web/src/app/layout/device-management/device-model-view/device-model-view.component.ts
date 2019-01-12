import { Component, OnInit } from '@angular/core';
import {DeviceModelViewElement, ParameterListElement} from "../../../models/device-elements";
import {ActivatedRoute} from "@angular/router";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../device.service";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";

@Component({
  selector: 'app-device-model-view',
  templateUrl: './device-model-view.component.html',
  styleUrls: ['./device-model-view.component.scss']
})
export class DeviceModelViewComponent implements OnInit {

    deviceModel: DeviceModelViewElement;
    parameters: ParameterListElement[];
    roles:UserRoles;

    constructor(
        private route: ActivatedRoute,
        private sessionContextService: SessionContextService,
        private deviceService: DeviceService,
        private userService:UserService
    ) {}

    ngOnInit() {
        this.getDevice();
        this.getParameters();
        this.getLoggedUserRoles();
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getDevice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getDeviceModelView(id).subscribe(x => this.deviceModel = x);
    }

    getParameters() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllParametersForDeviceModel(Number(id)).subscribe(parameters => {
            this.parameters = parameters
        });
    }
}
