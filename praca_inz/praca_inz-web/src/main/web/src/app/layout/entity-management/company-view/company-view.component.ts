import {Component, OnInit} from '@angular/core';
import {ProfileInfo} from "../../../models/profile-info";
import {DeviceListElement} from "../../../models/device-list-element";
import {TransferListElement} from "../../../models/transfer-list-element";
import {ProfileService} from "../../profile/profile.service";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../../device-management/device.service";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {StructureViewElement} from "../../../models/structure-view-element";
import {ActivatedRoute} from "@angular/router";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {StructureListElement} from "../../../models/structure-list-element";

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

    constructor(
        private route: ActivatedRoute,
        private companyService: CompanyService,
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
