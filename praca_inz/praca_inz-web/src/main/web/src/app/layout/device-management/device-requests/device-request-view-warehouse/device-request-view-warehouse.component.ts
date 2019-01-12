import { Component, OnInit } from '@angular/core';
import {ChangeRequestStatusElement, RequestViewElement} from "../../../../models/request-elements";
import {UserRoles} from "../../../../models/user-roles";
import {LoggedUser} from "../../../../models/logged-user";
import {DeviceListElement} from "../../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../employee-management/request.service";
import {Configuration} from "../../../../app.constants";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {DeviceService} from "../../device.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-device-request-view-warehouse',
  templateUrl: './device-request-view-warehouse.component.html',
  styleUrls: ['./device-request-view-warehouse.component.scss']
})
export class DeviceRequestViewWarehouseComponent implements OnInit {


    form = new FormControl();
    request: RequestViewElement;
    changeRequestStatusElement:ChangeRequestStatusElement=new ChangeRequestStatusElement();
    roles:UserRoles;
    currentUser:LoggedUser;

    selectedOptions:string[];
    devices = new Map<string, number>();
    devicesIds:number[];

    constructor(
        private route: ActivatedRoute,
        private requestService: RequestService,
        private configuration:Configuration,
        private userService:UserService,
        private deviceService:DeviceService,
        private  router:Router
    ) {}

    ngOnInit() {
        this.getRequest();
        this.getLoggedUser();
        this.getLoggeduserRoles();
        this.getDevices();
    }

    getDevices() {
        this.deviceService.getAllDevicesForWarehouseman().subscribe((response: DeviceListElement[]) => {
            this.devices = response.reduce(function (deviceMap, device) {
                if (device.id) {
                    deviceMap.set(device.deviceModel+" "+device.serialNumber, device.id)
                }
                return deviceMap;
            }, this.devices);
        });
    }

    getLoggeduserRoles(){
        this.userService.getLoggedUserRoles().subscribe(x=>this.roles=x);
    }

    getLoggedUser(){
        this.userService.getLoggedUser().subscribe(x=>this.currentUser=x);
    }

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => this.request = x);
    }

    reject() {
        this.changeRequestStatusElement.id=this.request.id;
        this.changeRequestStatusElement.version=this.request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_REJECTED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
            this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
        });

    }

    submit() {
        for(let device in this.selectedOptions){
            this.devicesIds.push(this.devices.get(device));
        }
        const id = this.route.snapshot.paramMap.get('id');
        this.changeRequestStatusElement.id=this.request.id;
        this.changeRequestStatusElement.version=this.request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_TO_TAKE_AWAY;
        this.requestService.addDevicesToRequest(this.devicesIds,Number(id)).subscribe(rep=>{
            this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
            });
        });

    }
}
