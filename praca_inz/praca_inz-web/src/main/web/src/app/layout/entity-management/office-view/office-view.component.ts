import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../../device-management/device.service";
import {StructureListElement, StructureViewElement} from "../../../models/structure-elements";
import {DeviceListElement} from "../../../models/device-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../../warehouse-management/warehouse.service";


@Component({
  selector: 'app-office-view',
  templateUrl: './office-view.component.html',
  styleUrls: ['./office-view.component.scss']
})
export class OfficeViewComponent implements OnInit {

    office: StructureViewElement;
    warehouses:WarehouseListElement[];
    devices: DeviceListElement[];


    constructor(
        private route: ActivatedRoute,
        private warehouseService: WarehouseService,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private sessionContextService: SessionContextService,
        private deviceService: DeviceService
    ) {}

    ngOnInit() {
        this.getOffice();
        this.getDevicesForOffice();
        this.getDevicesForOffice();
        this.getWarehouseForOffice();
    }

    getOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getOfficeView(id).subscribe(x => this.office = x);
    }


    getDevicesForOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllDevicesForDepartment(id).subscribe(deviceListElement => {
            this.devices = deviceListElement
        });
    }

    getWarehouseForOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllForOffice(id).subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }

    getAddressStructure(structure:StructureListElement): string {
        if (structure.flatNumber == null || structure.flatNumber === "0") {
            return (structure.street + ' ' + structure.buildingNumber);
        } else {
            return (structure.street + ' ' + structure.buildingNumber + ' / ' + structure.flatNumber);
        }
    }

}
