export class DeviceListElement {
    id: number;
    name: string;
    serialNumber: string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    username:string;

    toString(): string {
        return this.name+' '+ this.serialNumber + ' ' + this.deviceTypeName+' '+ this.username;
    }
}

export class DeviceTypeListElement {
    id: number;
    name: string;

    toString(): string {
        return this.name;
    }
}
