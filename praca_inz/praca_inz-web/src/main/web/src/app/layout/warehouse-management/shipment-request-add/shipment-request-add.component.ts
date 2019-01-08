import {Component, Input, OnInit} from '@angular/core';
import {ShipmentRequestAddElement, TransferRequestAddElement} from "../../../models/request-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {WarehouseService} from "../warehouse.service";
import {RequestService} from "../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceListElement, DeviceModelListElement} from "../../../models/device-elements";
import {DeviceService} from "../../device-management/device.service";

@Component({
  selector: 'app-shipment-request-add',
  templateUrl: './shipment-request-add.component.html',
  styleUrls: ['./shipment-request-add.component.scss']
})
export class ShipmentRequestAddComponent implements OnInit {
    @Input() shipmentRequestAddElement: ShipmentRequestAddElement= new ShipmentRequestAddElement;

    devices: DeviceListElement[]
    devicesModels:DeviceModelListElement[]
    warehouses: WarehouseListElement[];
    selectedWarehouse: string;
    selectedDevice:string;


    constructor(private route: ActivatedRoute,private deviceService:DeviceService,private warehouseService:WarehouseService,private requestService:RequestService,private translate:TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getWarehouses();
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevicesForShipmentRequest().subscribe(devices => {
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
        this.deviceService.getAllDevicesForShipmentRequest().subscribe(devices=> {this.devices=devices});
        this.deviceService.getAllDevicesModels().subscribe(devicesModels=>{this.devicesModels=devicesModels});
    }

    shipmentRequestAdd(){
        this.shipmentRequestAddElement.recieverWarehouseId=this.warehouses.find(x=>x.name==this.selectedWarehouse).id
        this.shipmentRequestAddElement.deviceId = this.devices.find(x=>x.name==this.selectedDevice).id;
        this.requestService.createShipmentRequest(this.shipmentRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.shipmentRequestAddElement=new ShipmentRequestAddElement();
    }
}
