export class StructureListElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    zipCode: string;
    parentName: string;

    toString(): string {
        return this.name+' '+ this.city + ' ' + this.name+' '+ this.parentName;
    }
}
