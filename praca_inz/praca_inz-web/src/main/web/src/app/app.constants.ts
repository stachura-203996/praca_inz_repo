import {Injectable} from '@angular/core';

@Injectable()
export class Configuration {

  // public Server = 'https://studapp.it.p.lodz.pl:8404/ssbd201804'; // prod
  public Server = 'http://localhost:8081';
  // public Server = ''; // npm start

  public SecuredUrl = '/secured';
  public ServerWithApiUrl = this.Server + this.SecuredUrl;
  public ROOT = '/';


    DEVICE_REQUEST: string = "DEVICE_REQUEST";
    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";
    DELIVERY_REQUEST: string = "DELIVERY_REQUEST";
    SHIPMENT_REQUEST: string = "SHIPMENT_REQUEST";

    REQUEST_STATUS_ACCEPTED:string="ACCEPTED";
    REQUEST_STATUS_REJECTED:string="REJECTED";
    REQUEST_STATUS_CANCELED:string="CANCELED";
    REQUEST_STATUS_TO_TAKE_AWAY:string="TO_TAKE_AWAY";


}
