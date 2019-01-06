import {Component, Input, OnInit} from '@angular/core';
import {ShipmentRequestAddElement, ShipmentRequestEditElement} from "../../../models/request-elements";
import {DeviceListElement, DeviceModelListElement} from "../../../models/device-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../device-management/device.service";
import {WarehouseService} from "../warehouse.service";
import {RequestService} from "../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-shipment-request-edit',
  templateUrl: './shipment-request-edit.component.html',
  styleUrls: ['./shipment-request-edit.component.scss']
})
export class ShipmentRequestEditComponent implements OnInit {

   shipmentRequestEditElement: ShipmentRequestEditElement;

    devices: DeviceListElement[]
    devicesModels:DeviceModelListElement[]
    warehouses: WarehouseListElement[];
    selectedWarehouse: string;
    selectedDevice:string;


    constructor(private route: ActivatedRoute,private deviceService:DeviceService,private warehouseService:WarehouseService,private requestService:RequestService,private translate:TranslateService,private router:Router) {

    }

    ngOnInit() {
        this.getWarehouses();
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevicesForRequest().subscribe(devices => {
            if (!devices) {
                this.devices = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.devices = devices;
            }

            searchText = searchText.toLowerCase();
            this.devices = devices.filter(it => {
                const range = it.deviceModel;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    getWarehouses(){
        this.warehouseService.getAllForShipmentRequest().subscribe(warehouses=> {this.warehouses=warehouses});
        this.deviceService.getAllDevicesForRequest().subscribe(devices=> {this.devices=devices});
        this.deviceService.getAllDevicesModels().subscribe(devicesModels=>{this.devicesModels=devicesModels});
    }

    shipmentRequestAdd(){
        this.shipmentRequestEditElement.recieverWarehouseId=this.warehouses.find(x=>x.name==this.selectedWarehouse).id
        this.shipmentRequestEditElement.deviceId = this.devices.find(x=>x.name==this.selectedDevice).id;
        this.requestService.createShipmentRequest(this.shipmentRequestEditElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.shipmentRequestEditElement=new ShipmentRequestEditElement();
    }
}
