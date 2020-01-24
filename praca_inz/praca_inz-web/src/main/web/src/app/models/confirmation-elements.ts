export class ConfirmationListElement {
    id: number;
    title: string;
    confirmationDate: string;
    description: string;
    sender:string;
    senderName:string;
    senderSurname:string;
    receiver:string;
    recieverName:string;
    recieverSurname:string
}
export class ConfirmationAddElement {
    title: string;
    description: string;
    reciever:number
}

export class ConfirmationViewElement {
    id: number;
    title: string;
    confirmationDate: string;
    description: string;
    sender:string;
    receiver:string;
    senderName:string;
    senderSurname:string;
    recieverName:string;
    recieverSurname:string
}
