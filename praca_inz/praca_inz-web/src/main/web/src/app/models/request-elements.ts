export class RequestListElement {
    id: number;
    title: string;
    serialNumber: string;
    deviceTypeName: string;
    username: string;
    status: string;
    acceptedToSend: boolean;
    acceptedToRecive: boolean;
    recieverWarehouseName:string;
    senderWarehouseName:string;
    utilTimestamp:string;

    toString(): string {
        return this.title + ' ' + this.serialNumber + ' ' + this.deviceTypeName + ' ' + this.username;
    }
}
