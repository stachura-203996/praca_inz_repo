import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../../administration/user-management/user.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {DeviceAddElement, DeviceEditElement, DeviceModelListElement} from "../../../../../../models/device-elements";
import {LoggedUser} from "../../../../../../models/logged-user";
import {DeviceService} from "../../../../../device-management/device.service";
import {CompanyService} from "../../../administration/company/company.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {StructureListElement} from "../../../../../../models/structure-elements";

@Component({
    selector: 'app-device-edit',
    templateUrl: './device-edit.component.html',
    styleUrls: ['./device-edit.component.scss']
})
export class DeviceEditComponent implements OnInit {


    deviceEditElement: DeviceEditElement;

    deviceModels = new Map<string, number>();
    warehouses = new Map<string, number>();
    companies = new Map<string, number>();

    selectedModel: string;
    selectedWarehouse: string;
    selectedCompany: string;

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private route: ActivatedRoute,
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
        this.getDevice();
        this.getDevicesModels();
        this.getWarehouses();
        this.getCompanies();
    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getDevice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getDeviceEdit(id).subscribe(x=>this.deviceEditElement=x);
    }

    getDevicesModels() {
        this.deviceService.getAllDevicesModels().subscribe((response: DeviceModelListElement[]) => {
            this.deviceModels = response.reduce(function (deviceModelMap, deviceModel) {
                if (deviceModel.id) {
                    deviceModelMap.set(deviceModel.name, deviceModel.id)
                }
                return deviceModelMap;
            }, this.deviceModels);
        });
    }

    getWarehouses() {
        this.warehouseService.getAll().subscribe((response: WarehouseListElement[]) => {
            this.warehouses = response.reduce(function (warehouseMap, warehouse) {
                if (warehouse.id) {
                    warehouseMap.set(warehouse.name, warehouse.id)
                }
                return warehouseMap;
            }, this.warehouses);
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

    deviceEdit() {
        if (this.roles.admin) {
            this.deviceEditElement.companyId = this.companies.get(this.selectedCompany);
        } else {
            this.deviceEditElement.companyId = this.currentUser.companyId;
        }
        this.deviceEditElement.warehouseId = this.warehouses.get(this.selectedWarehouse);
        this.deviceEditElement.deviceModelId = this.deviceModels.get(this.selectedModel);
        this.deviceService.updateDevice(this.deviceEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/devices');
        });

    }

    clear() {
        this.deviceEditElement = new DeviceEditElement();
    }

}
