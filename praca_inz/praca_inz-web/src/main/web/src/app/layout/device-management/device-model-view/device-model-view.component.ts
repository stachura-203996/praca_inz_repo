import { Component, OnInit } from '@angular/core';
import {StructureListElement, StructureViewElement} from "../../../models/structure-elements";
import {CompanyService} from "../../admin/components/administration/company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {
    DeviceListElement,
    DeviceModelViewElement,
    DeviceViewElement,
    ParameterListElement
} from "../../../models/device-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {ActivatedRoute} from "@angular/router";
import {WarehouseService} from "../../warehouse-management/warehouse.service";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {SessionContextService} from "../../../shared/services/session-context.service";
import {DeviceService} from "../device.service";

@Component({
  selector: 'app-device-model-view',
  templateUrl: './device-model-view.component.html',
  styleUrls: ['./device-model-view.component.scss']
})
export class DeviceModelViewComponent implements OnInit {

    deviceModel: DeviceModelViewElement;
    parameters: ParameterListElement[];

    constructor(
        private route: ActivatedRoute,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private sessionContextService: SessionContextService,
        private deviceService: DeviceService
    ) {}

    ngOnInit() {
        this.getDeviceModel();
        this.getParameters();
    }

    getDeviceModel() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getDeviceModelView(id).subscribe(x => this.deviceModel = x);
    }

    getParameters() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllParametersForDeviceModel(id).subscribe(deviceListElement => {
            this.parameters = deviceListElement
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
