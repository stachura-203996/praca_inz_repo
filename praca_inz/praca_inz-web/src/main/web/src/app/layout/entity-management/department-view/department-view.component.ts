import { Component, OnInit } from '@angular/core';
import {StructureViewElement} from "../../../models/structure-view-element";
import {DeviceListElement} from "../../../models/device-list-element";
import {StructureListElement} from "../../../models/structure-list-element";
import {ActivatedRoute} from "@angular/router";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../../device-management/device.service";

@Component({
  selector: 'app-department-view',
  templateUrl: './department-view.component.html',
  styleUrls: ['./department-view.component.scss']
})
export class DepartmentViewComponent implements OnInit {

    department: StructureViewElement;
    devices: DeviceListElement[];
    offices: StructureListElement[];

    constructor(
        private route: ActivatedRoute,
        private companyService: CompanyService,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private sessionContextService: SessionContextService,
        private deviceService: DeviceService
    ) {}

    ngOnInit() {
        this.getDepartment();
        this.getDevicesForOffice();
        this.getOfficesForDepartment();
    }

    getDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getDepartmentView(id).subscribe(x => this.department = x);
    }


    getOfficesForDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getAllForDepartment(id).subscribe(officeListElement => {
            this.offices = officeListElement
        });
    }

    getDevicesForOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllDevicesForDepartment(id).subscribe(deviceListElement => {
            this.devices = deviceListElement
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
