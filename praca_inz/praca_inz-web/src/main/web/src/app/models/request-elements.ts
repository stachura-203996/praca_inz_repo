//LIST

export class RequestListElement {
    id: number;
    title: string;
    username: string;
    status: string;
    type:string
    recieverWarehouseName:string;
    senderWarehouseName:string;
    createDate:string;
    version:string
}

export class ChangeRequestStatusElement{
    id: number;
    status: string;
    version:string;
}
//ADD

export class TransferRequestAddElement {
    id: number;
    title: string;
    description: string;
    deviceId:string;
    recieverWarehouseId:number;
}

export class DeviceRequestAddElement {
    id: number;
    title: string;
    amount:string;
    description: string;
    deviceModelId:number;
}

export class DeliveryRequestAddElement {
    id: number;
    title: string;
    amount:string;
    description: string;
    deviceModel:number;
}


//VIEW

export class RequestViewElement {
    id: number;
    title: string;
    username: string;
    status: string;
    type:string;
    deviceModelName:string;
    recieverWarehouseName:string;
    senderWarehouseName:string;
    createDate:string;
    description:string;
    version:string;
    amount:number;
}


