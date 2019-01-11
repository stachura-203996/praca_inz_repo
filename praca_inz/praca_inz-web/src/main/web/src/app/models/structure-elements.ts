export class StructureAddElement {
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    companyId:number;
    departmentId:number;
}

export class StructureEditElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    parentId: number;
    version:string;
}

export class StructureListElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    companyName: string;
    departmentName:string;
    deleted:boolean;
}

export class StructureViewElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    companyName: string;
    departmentName:string;
}


