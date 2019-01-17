import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../administration/user-management/user.service";
import {WarehouseAddElement, WarehouseEditElement} from "../../../../../../models/warehouse-elements";
import {OfficeService} from "../../office/office.service";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {UserListElement} from "../../../../../../models/user-list-element";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

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
        private messageService:MessageService,
        private configuration:Configuration,
        private router: Router) {

    }

    ngOnInit() {
        this.getWarehouse();
        this.getOffices();
        this.getWarehousesmen();

    }

    getWarehouse() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getWarehouseEdit(id).subscribe(x => {this.warehouseEditElement = x});
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
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('warehouse.edit').subscribe(x=>entity=x);
        this.translate.get('confirm.edit').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.warehouseEditElement.officeId = this.offices.get(this.warehouseEditElement.officeName);
                    this.warehouseEditElement.userId = this.warehousemen.get(this.warehouseEditElement.selectedUser);

                    this.warehouseService.updateWarehouse(this.warehouseEditElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/warehouses');
                        this.translate.get('success.warehouse.edit').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    clear() {
        this.warehouseEditElement = new WarehouseEditElement();
    }
}
