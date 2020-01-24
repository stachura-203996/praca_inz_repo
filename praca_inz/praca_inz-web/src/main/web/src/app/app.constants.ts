import {Injectable} from '@angular/core';

@Injectable()
export class Configuration {


    public Server = 'https://localhost:8081';

    public SecuredUrl = '/secured';
    public ServerWithApiUrl = this.Server + this.SecuredUrl;
    public ROOT = '/';


    public DEVICE_REQUEST: string = "DEVICE_REQUEST";
    public TRANSFER_REQUEST: string = "TRANSFER_REQUEST";

    public REQUEST_STATUS_ACCEPTED: string = "ACCEPTED";
    public REQUEST_STATUS_REJECTED: string = "REJECTED";
    public REQUEST_STATUS_CANCELED: string = "CANCELED";
    public REQUEST_STATUS_TO_TAKE_AWAY: string = "TO_TAKE_AWAY";

    public OPTIMISTIC_LOCK = 'error_optimistic_lock';
    public ERROR_NO_OBJECT_IN_DATABASE = 'error_no_object_in_database';


    // User response codes
    public ERROR_EMAIL_TAKEN = 'email_taken';
    public ERROR_USERNAME_TAKEN = 'un_taken';
    public SUCCESS = 'success'
    public ERROR_SAME_PASSWORD = 'same_password';
    public ERROR_NO_USERNAME_IN_DATABASE = 'error_no_username_in_database';
    public ERROR_INCORRECT_PASSWORD_RESET_DATA = 'incorect_password_reset_data';

    //Company response codes
    public ERROR_COMPANY_NAME_TAKEN = "cn_taken";

    //Department response codes
    public ERROR_DEPARTMENT_NAME_TAKEN = "dn_taken";

    //Office response codes
    public ERROR_OFFICE_NAME_TAKEN = "on_taken";

    //Warehouse response codes
    public ERROR_WAREHOUSE_NAME_TAKEN = "wn_taken";

    //Device response codes
    public ERROR_SERIAL_NUMBER_NAME_TAKEN = "sn_taken";

    //Device mode response codes
    public ERROR_DEVICE_MODEL_NAME_NAME_TAKEN = "dmn_taken";
}
