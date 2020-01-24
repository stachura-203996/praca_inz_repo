export class DeviceListElement {
    id: number;
    serialNumber: string;
    deviceModel:string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    status:string;
    responsibleFor:string;
}

export class DeviceAddElement {
    serialNumber: string;
    companyId:number;
    warehouseId:number;
    deviceModelId:number;
}

export class DeviceEditElement {
    id:number
    serialNumber: string;
    companyId:number;
    warehouseId:number;
    deviceModelId:number;
    companyName:string;
    deviceModelName:string;

    version:string;
}

export class DeviceViewElement {
    id: number;
    serialNumber: string;
    deviceModel:string;
    deviceModelId:number;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    responsibleFor:string;
    status:string;
}

export class DeviceModelViewElement {
    id: number;
    name: string;
    manufacture:string;
    numberOfDevices:number;
    type:string;
    cost:string;
    companyName:string;
}
export class DeviceModelAddElement {
    name: string;
    manufacture:string;
    typeId:number;
    cost:string
    companyId:number
}

export class DeviceModelEditElement {
    id: number;
    name: string;
    manufacture:string;
    type:string;
    typeId:number;
    cost:string
    companyId:number
    companyname:string;
    version:string;

}

export class DeviceModelListElement {
    id: number;
    name: string;
    manufacture:string;
    numberOfDevices:number;
    cost:string
    deviceTypeName:string;
    companyName:string;
}

export class DeviceTypeListElement {
    id: number;
    name: string;
}

export class ParameterListElement{
    id: number;
    name: string;
    value:string;
}
