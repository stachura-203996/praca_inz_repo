import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../../administration/user-management/user.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {DeviceEditElement, DeviceModelListElement} from "../../../../../../models/device-elements";
import {LoggedUser} from "../../../../../../models/logged-user";
import {DeviceService} from "../../../../../device-management/device.service";
import {CompanyService} from "../../../administration/company/company.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

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
        this.getDevice();
        this.getDevicesModels();
        this.getWarehouses();
        this.getCompanies();
    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x =>{ this.currentUser = x}, error => {
            if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
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

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => {this.roles = x}, error => {
            if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
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

    getDevice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getDeviceEdit(id).subscribe(x=>{this.deviceEditElement=x}, error => {
            if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
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
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('device.edit').subscribe(x=>entity=x);
        this.translate.get('confirm.edit').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    if (this.roles.admin) {
                        this.deviceEditElement.companyId = this.companies.get(this.deviceEditElement.companyName);
                    } else {
                        this.deviceEditElement.companyId = this.currentUser.companyId;
                    }
                    this.deviceEditElement.deviceModelId = this.deviceModels.get(this.deviceEditElement.deviceModelName);
                    this.deviceService.updateDevice(this.deviceEditElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/devices');
                        this.translate.get('success.device.edit').subscribe(x => {
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.OPTIMISTIC_LOCK) {
                            this.translate.get('optimistic.lock').subscribe(x => {
                                this.messageService.error(x);
                            })

                        } else  if (error === this.configuration.ERROR_SERIAL_NUMBER_NAME_TAKEN) {
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
        this.deviceEditElement = new DeviceEditElement();
    }

}
