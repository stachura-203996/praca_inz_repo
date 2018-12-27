export class NotificationListElement {
    id: number;
    title: string;
    description: string;
    url:string;
    calendarTimestamp:string;
    readed:boolean;

    toString(): string {
        return this.title+' '+ this.description + ' ' + this.url+' '+ this.calendarTimestamp;
    }
}
