export class RequestListElement {
    id: number;
    title: string;
    username: string;
    status: string;
    acceptedToSend: boolean;
    acceptedToRecive: boolean;
    recieverWarehouseName:string;
    senderWarehouseName:string;
    utilTimestamp:string;

    toString(): string {
        return this.title + ' ' + this.username + ' '+ this.recieverWarehouseName+' '+ this.senderWarehouseName+''+this.utilTimestamp;
    }
}
