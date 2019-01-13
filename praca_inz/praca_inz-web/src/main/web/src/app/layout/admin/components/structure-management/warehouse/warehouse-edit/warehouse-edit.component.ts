import {Component, Input, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {WarehouseAddElement, WarehouseEditElement} from "../../../../../../models/warehouse-elements";
import {OfficeService} from "../../office/office.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {UserListElement} from "../../../../../../models/user-list-element";

@Component({
    selector: 'app-warehouse-edit',
    templateUrl: './warehouse-edit.component.html',
    styleUrls: ['./warehouse-edit.component.scss']
})
export class WarehouseEditComponent implements OnInit {

    warehouseEditElement: WarehouseEditElement;

    offices = new Map<string, number>();
    warehousemen = new Map<string, number>();

    constructor(
        private route: ActivatedRoute,
        private officeService: OfficeService,
        private userService: UserService,
        private warehouseService: WarehouseService,
        private translate: TranslateService,
        private router: Router) {

    }

    ngOnInit() {
        this.getWarehouse();
        this.getOffices();


    }

    getWarehouse() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getWarehouseEdit(id).subscribe(x => this.warehouseEditElement = x);
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


    getWarehousesmen() {
        this.userService.getAllWarehousemen(this.offices.get(this.warehouseEditElement.officeName)).subscribe((response: UserListElement[]) => {
            this.warehousemen = response.reduce(function (warehousemanMap, warehouseman) {
                if (warehouseman.id) {
                    warehousemanMap.set(warehouseman.name+" "+warehouseman.surname+" | "+warehouseman.username, warehouseman.id)
                }
                return warehousemanMap;
            }, this.warehousemen);
        });
    }

    warehouseUpdate() {

        this.warehouseEditElement.officeId = this.offices.get(this.warehouseEditElement.officeName);
        this.warehouseEditElement.userId = this.warehousemen.get(this.warehouseEditElement.selectedUser);

        this.warehouseService.createWarehouse(this.warehouseEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });

    }

    clear() {
        this.warehouseEditElement = new WarehouseEditElement();
    }
}
