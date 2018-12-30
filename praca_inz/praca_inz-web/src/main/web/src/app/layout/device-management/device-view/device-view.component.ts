import { Component, OnInit } from '@angular/core';
import {StructureListElement, StructureViewElement} from "../../../models/structure-elements";
import {DeviceListElement} from "../../../models/device-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {ActivatedRoute} from "@angular/router";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {WarehouseService} from "../../warehouse-management/warehouse.service";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../device.service";

@Component({
  selector: 'app-device-view',
  templateUrl: './device-view.component.html',
  styleUrls: ['./device-view.component.scss']
})
export class DeviceViewComponent implements OnInit {

    company: StructureViewElement;
    devices: DeviceListElement[];
    offices: StructureListElement[];
    departments: StructureListElement[];
    warehouses:WarehouseListElement[];

    constructor(
        private route: ActivatedRoute,
        private companyService: CompanyService,
        private warehouseService:WarehouseService,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private sessionContextService: SessionContextService,
        private deviceService: DeviceService
    ) {}

    ngOnInit() {
        this.getCompany();
        this.getDevicesForCompany();
        this.getOfficesForCompany();
        this.getDepartmentsForCompany();
    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.companyService.getCompany(id).subscribe(x => this.company = x);
    }

    getDepartmentsForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getAllForCompany(id).subscribe(departmentListElement => {
            this.departments = departmentListElement
        });
    }

    getOfficesForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getAllForCompany(id).subscribe(officeListElement => {
            this.offices = officeListElement
        });
    }

    getWarehouseForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllForCompany(id).subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }

    getDevicesForCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllDevicesForCompany(id).subscribe(deviceListElement => {
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

}
