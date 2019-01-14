import {Component, Input, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {CompanyService} from "../../../administration/company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {DeviceAddElement, DeviceModelListElement} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

@Component({
    selector: 'app-device-add',
    templateUrl: './device-add.component.html',
    styleUrls: ['./device-add.component.scss']
})
export class DeviceAddComponent implements OnInit {


    @Input() deviceAddElement: DeviceAddElement = new DeviceAddElement();

    deviceModels = new Map<string, number>();
    warehouses = new Map<string, number>();
    companies = new Map<string, number>();

    selectedModel: string;
    selectedWarehouse: string;
    selectedCompany: string;

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private deviceService: DeviceService,
        private companyService: CompanyService,
        private warehouseService: WarehouseService,
        private userService: UserService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router: Router) {

    }

    ngOnInit() {
        this.getLoggedUser();
        this.getLoggedUserRoles();
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

    deviceAdd() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    if (this.roles.admin) {
                        this.deviceAddElement.companyId = this.companies.get(this.selectedCompany);
                    } else {
                        this.deviceAddElement.companyId = this.currentUser.companyId;
                    }
                    this.deviceAddElement.warehouseId = this.warehouses.get(this.selectedWarehouse);
                    this.deviceAddElement.deviceModelId = this.deviceModels.get(this.selectedModel);
                    this.deviceService.createDevice(this.deviceAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/devices');
                        this.translate.get('success.device.add').subscribe(x => {
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.ERROR_SERIAL_NUMBER_NAME_TAKEN) {
                            this.translate.get('device.serial.number.taken.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
                            this.translate.get('no.object.in.database.error').subscribe(x => {
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
        this.deviceAddElement = new DeviceAddElement();
    }

}
