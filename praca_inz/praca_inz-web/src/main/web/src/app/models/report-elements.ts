export class ReportListElement {
    id: number;
    title: string;
    reportDate: string;
    description: string;
    sender:string;
    senderName:string;
    senderSurname:string;
    receiver:string;
    recieverName:string;
    recieverSurname:string
}
export class ReportAddElement {
    title: string;
    description: string;
    reciever:number
}

export class ReportViewElement {
    id: number;
    title: string;
    reportDate: string;
    description: string;
    sender:string;
    receiver:string;
    senderName:string;
    senderSurname:string;
    recieverName:string;
    recieverSurname:string
}
