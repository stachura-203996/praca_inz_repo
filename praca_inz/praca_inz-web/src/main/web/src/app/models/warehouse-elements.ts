export class WarehouseListElement {
    id: number;
    name: string;
    userName:string;
    userSurname:string;
    username: string;
    officeName: string;
    companyName: string;
    departmentName: string;
    devicesNumber:string;
    deleted:boolean;
}

export class WarehouseViewElement {
    name: string;
    userName: string;
    userSurname: string;
    username: string;
    officeName: string;
    companyName: string;
    departmentName: string;
    devicesNumber: string;

}

export class WarehouseEditElement {
    id: number;
    name: string;
    userId:number;
    officeId:number;
    version:number;
}


export class WarehouseAddElement {
    name: string;
    userId:number;
    officeId:number;
}

export class DeliveryListElement{
    id:number;
    deliveryNumber:string;
    title:string;
    username:string;
    status:string;
    description:string
    createDate:string;
    lastUpdate:string;
    sender:string;
    receiver:string
}

