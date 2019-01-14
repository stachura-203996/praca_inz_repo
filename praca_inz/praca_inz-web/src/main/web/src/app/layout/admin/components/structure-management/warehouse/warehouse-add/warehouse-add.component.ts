import {Component, Input, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {OfficeService} from "../../office/office.service";
import {UserListElement} from "../../../../../../models/user-list-element";
import {WarehouseAddElement} from "../../../../../../models/warehouse-elements";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";

@Component({
    selector: 'app-warehouse-add',
    templateUrl: './warehouse-add.component.html',
    styleUrls: ['./warehouse-add.component.scss']
})
export class WarehouseAddComponent implements OnInit {

    @Input() warehouseAddElement: WarehouseAddElement = new WarehouseAddElement;

    offices = new Map<string, number>();
    warehousemen = new Map<string, number>();


    selectedOffice: string;
    selectedWarehouseman: string;

    constructor(
        private officeService: OfficeService,
        private userService: UserService,
        private warehouseService: WarehouseService,
        private translate: TranslateService,
        private router: Router) {

    }

    ngOnInit() {
        this.getOffices();
    }

    getOffices() {
        this.officeService.getAll().subscribe((response: StructureListElement[]) => {
            this.offices = response.reduce(function (officeMap, office) {
                if (office.id) {
                    officeMap.set(office.name, office.id)
                }
                return officeMap;
            }, this.offices);
        });
    }

    getWarehousesman() {
        this.warehousemen=new Map<string, number>();
        this.userService.getAllWarehousemen(this.offices.get(this.selectedOffice)).subscribe((response: UserListElement[]) => {
            this.warehousemen = response.reduce(function (warehousemanMap, warehouseman) {
                if (warehouseman.id) {
                    warehousemanMap.set(warehouseman.name+" "+warehouseman.surname+" | "+warehouseman.username, warehouseman.id)
                }
                return warehousemanMap;
            }, this.warehousemen);
        });
    }

    warehouseAdd() {

        this.warehouseAddElement.officeId = this.offices.get(this.selectedOffice);
        this.warehouseAddElement.userId = this.warehousemen.get(this.selectedWarehouseman);

        this.warehouseService.createWarehouse(this.warehouseAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/warehouses');
        });

    }

    clear() {
        this.warehouseAddElement = new WarehouseAddElement();
    }
}
