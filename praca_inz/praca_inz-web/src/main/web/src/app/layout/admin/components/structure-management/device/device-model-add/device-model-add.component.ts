import {Component, Input, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {
    DeviceAddElement, DeviceModelAddElement,
    DeviceModelListElement,
    DeviceTypeListElement,
    ParameterListElement
} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {CompanyService} from "../../../administration/company/company.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {StructureListElement} from "../../../../../../models/structure-elements";

@Component({
    selector: 'app-device-model-add',
    templateUrl: './device-model-add.component.html',
    styleUrls: ['./device-model-add.component.scss']
})
export class DeviceModelAddComponent implements OnInit {

    @Input() deviceModelAddElement: DeviceModelAddElement = new DeviceModelAddElement();

    parameterName: string;
    parameterValue: string;

    parameters=[];


    deviceTypes = new Map<string, number>();
    companies = new Map<string, number>();

    selectedType: string;
    selectedCompany: string;

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private deviceService: DeviceService,
        private companyService: CompanyService,
        private warehouseService: WarehouseService,
        private userService: UserService,
        private translate: TranslateService,
        private router: Router) {

    }

    ngOnInit() {
        this.getLoggedUser();
        this.getLoggedUserRoles();
        this.getDevicesModels();
        this.getCompanies();
    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getDevicesModels() {
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
            this.parameters.push(
                {
                    name: this.parameterName,
                    value: this.parameterValue
                }
            );
    }

    deleteParameter(name){

        for(var i=0; i< this.parameters.length; i++){
            if(this.parameters[i]["name"]==name){
                this.parameters.splice(i,1);
            }
        }

    }

    deviceAdd() {
        if (this.roles.admin) {
            this.deviceModelAddElement.companyId = this.companies.get(this.selectedCompany);
        } else {
            this.deviceModelAddElement.companyId = this.currentUser.companyId;
        }


        this.deviceModelAddElement.typeId = this.deviceTypes.get(this.selectedType);
        this.deviceService.createDeviceModel(this.deviceModelAddElement).subscribe(resp => {
            for(var i=0; i< this.parameters.length; i++){
              const parameter:ParameterListElement=new ParameterListElement();
              parameter.value=this.parameters[i]["value"];
              parameter.name=this.parameters[i]["name"];
              this.deviceService.createParameter(parameter,resp).subscribe();
              if(i==this.parameters.length-1){
                  this.router.navigateByUrl('/admin/devices/model');
              }
            }

        });

    }

    clear() {
        this.deviceModelAddElement = new DeviceModelAddElement();
        this.parameters=[];
    }

}
