import {Component, Input, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {DeviceModelEditElement, DeviceTypeListElement, ParameterListElement} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {CompanyService} from "../../../administration/company/company.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

@Component({
  selector: 'app-device-model-edit',
  templateUrl: './device-model-edit.component.html',
  styleUrls: ['./device-model-edit.component.scss']
})
export class DeviceModelEditComponent implements OnInit {

   deviceModelEditElement: DeviceModelEditElement;

    parameterName: string;
    parameterValue: string;

    parameters: ParameterListElement[];

    deviceTypes = new Map<string, number>();
    companies = new Map<string, number>();

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private route: ActivatedRoute,
        private deviceService: DeviceService,
        private companyService: CompanyService,
        private warehouseService: WarehouseService,
        private userService: UserService,
        private translate: TranslateService,
        private messageService:MessageService,
        private configuration:Configuration,
        private router: Router) {

    }

    ngOnInit() {
        this.getLoggedUser();
        this.getLoggedUserRoles();
        this.getDevicesTypes();
        this.getCompanies();
        this.getDeviceModel();
        this.getParameters();
    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => {this.currentUser = x});
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => {this.roles = x});
    }

    getDeviceModel() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getDeviceModelEdit(id).subscribe(x=>{this.deviceModelEditElement=x});
    }

    getParameters() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllParametersForDeviceModel(Number(id)).subscribe(parameters => {
            this.parameters = parameters
        });
    }

    getDevicesTypes() {
        this.deviceService.getAllDevicesTypes().subscribe((response: DeviceTypeListElement[]) => {
            this.deviceTypes = response.reduce(function (deviceTypeMap, deviceType) {
                if (deviceType.id) {
                    deviceTypeMap.set(deviceType.name, deviceType.id)
                }
                return deviceTypeMap;
            }, this.deviceTypes);
        });
    }

    getCompanies() {
        this.companyService.getAll().subscribe((response: StructureListElement[]) => {
            this.companies = response.reduce(function (companyMap, company) {
                if (company.id) {
                    companyMap.set(company.name, company.id)
                }
                return companyMap;
            }, this.companies);
        });
    }

    addParameter() {
        const id = this.route.snapshot.paramMap.get('id');
        var parameter:ParameterListElement= new ParameterListElement();
        parameter.name=this.parameterName;
        parameter.value=this.parameterValue;
        this.deviceService.createParameter(parameter,Number(id)).subscribe(rep=>{
            this.getParameters();
        });
    }

    deleteParameter(id:number){

       this.deviceService.deleteParameter(id).subscribe(rep=>{
           this.getParameters();
       });
    }

    deviceUpdate() {
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('device.model.edit').subscribe(x=>entity=x);
        this.translate.get('confirm.edit').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    if (this.roles.admin) {
                        this.deviceModelEditElement.companyId = this.companies.get(this.deviceModelEditElement.companyname);
                    } else {
                        this.deviceModelEditElement.companyId = this.currentUser.companyId;
                    }


                    this.deviceModelEditElement.typeId = this.deviceTypes.get(this.deviceModelEditElement.type);
                    this.deviceService.updateDeviceModel(this.deviceModelEditElement).subscribe(resp => {
                                this.router.navigateByUrl('/admin/devices/model');
                        this.translate.get('success.device.model.edit').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    clear() {
        this.deviceModelEditElement = new DeviceModelEditElement();
        this.parameters=[];
    }

}
