export class TransferListElement {
    id: number;
    title: string;
    date: Date;
    senderWarehouseName: string;
    recieverWarehouseName:string;
    deviceName:string;
    deviceSerialNumber:string;


    toString(): string {
        return this.title+' '+ this.date + ' ' + this.senderWarehouseName+' '+ this.recieverWarehouseName+ ' '+this.deviceName+' '+this.deviceSerialNumber;
    }
}
