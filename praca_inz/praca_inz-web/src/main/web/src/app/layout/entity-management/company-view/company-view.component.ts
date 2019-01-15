import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../device-management/device.service";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {ActivatedRoute} from "@angular/router";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {StructureListElement, StructureViewElement} from "../../../models/structure-elements";
import {DeviceListElement} from "../../../models/device-elements";
import {WarehouseService} from "../../warehouse-management/warehouse.service";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";


@Component({
    selector: 'app-company-view',
    templateUrl: './company-view.component.html',
    styleUrls: ['./company-view.component.scss']
})
export class CompanyViewComponent implements OnInit {

    company: StructureViewElement;
    devices: DeviceListElement[];
    offices: StructureListElement[];
    departments: StructureListElement[];
    warehouses:WarehouseListElement[];
    roles: UserRoles;

    constructor(
        private route: ActivatedRoute,
        private companyService: CompanyService,
        private warehouseService:WarehouseService,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private userService:UserService,
        private deviceService: DeviceService
    ) {}

    ngOnInit() {
        this.getCompany();
        this.getDevicesForCompany();
        this.getOfficesForCompany();
        this.getDepartmentsForCompany();
        this.getWarehouseForCompany();
        this.getLoggedUserRoles();
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.companyService.getCompany(id).subscribe(x => this.company = x);
    }

    getDepartmentsForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getAllForCompany(Number(id)).subscribe(departmentListElement => {
            this.departments = departmentListElement
        });
    }

    getOfficesForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getAllForCompany(Number(id)).subscribe(officeListElement => {
            this.offices = officeListElement
        });
    }

    getWarehouseForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllForCompany(Number(id)).subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }

    getDevicesForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllDevicesForCompany(Number(id)).subscribe(deviceListElement => {
            this.devices = deviceListElement
        });
    }

    getAddress(): string {
        if (this.company.flatNumber == null || this.company.flatNumber === "0") {
            return (this.company.street + ' ' + this.company.buildingNumber);
        } else {
            return (this.company.street + ' ' + this.company.buildingNumber + ' / ' + this.company.flatNumber);
        }
    }

    getAddressStructure(structure:StructureListElement): string {
        if (structure.flatNumber == null || structure.flatNumber === "0") {
            return (structure.street + ' ' + structure.buildingNumber);
        } else {
            return (structure.street + ' ' + structure.buildingNumber + ' / ' + structure.flatNumber);
        }
    }

    getPersonResponsibleFor(data:WarehouseListElement){
        return data.userName+' '+data.userSurname;
    }

}
