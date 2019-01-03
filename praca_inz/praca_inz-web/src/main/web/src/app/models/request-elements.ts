//LIST

export class RequestListElement {
    id: number;
    title: string;
    username: string;
    status: string;
    type:string
    acceptedToSend: boolean;
    acceptedToRecive: boolean;
    recieverWarehouseName:string;
    senderWarehouseName:string;
    createDate:string;
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
    description: string;
    deviceModel:number;
}

export class DeliveryRequestAddElement {
    id: number;
    title: string;
    description: string;
    deviceModel:string;
}

export class ShipmentRequestAddElement {
    id: number;
    title: string;
    description: string;
    deviceId:number;
    recieverWarehouseId:number;
}

//EDIT

export class TransferRequestEditElement {
    id: number;
    title: string;
    description: string;
    deviceId:string;
    recieverWarehouseId:number;
}

export class DeviceRequestEditElement {
    id: number;
    title: string;
    description: string;
    deviceModel:number
}

export class DeliveryRequestEditElement {
    id: number;
    title: string;
    description: string;
    deviceModel:number;
}

export class ShipmentRequestEditElement {
    id: number;
    title: string;
    description: string;
    deviceId:number;
    recieverWarehouseId:number;
}

//VIEW

export class RequestViewElement {
    id: number;
    title: string;
    username: string;
    status: string;
    type:string
    acceptedToSend: boolean;
    acceptedToRecive: boolean;
    recieverWarehouseName:string;
    senderWarehouseName:string;
    createDate:string;
}


