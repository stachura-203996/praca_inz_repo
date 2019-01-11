export class StructureAddElement {
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    zipCode: string;
    companyId:number;
    departmentId:string
}

export class StructureEditElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    zipCode: string;
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
    zipCode: string;
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
    zipCode: string;
    parentName: string;
}


