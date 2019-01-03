export class WarehouseListElement {
    id: number;
    name: string;
    username: string;
    officeId:number
    officeName: string;
    devicesNumber:string;

    toString(): string {
        return this.name+' '+ this.username + ' ' + this.officeName;
    }
}

export class WarehoucseListElement {
    id: number;
    name: string;
    username: string;
    officeId:number
    officeName: string;
    devicesNumber:string;

    toString(): string {
        return this.name+' '+ this.username + ' ' + this.officeName;
    }
}

export class WarehouseAddElement {
    id: number;
    name: string;
    username: string;
    officeId:number
    officeName: string;
    devicesNumber:string;

    toString(): string {
        return this.name+' '+ this.username + ' ' + this.officeName;
    }
}
