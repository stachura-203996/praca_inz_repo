import {Injectable} from '@angular/core';

@Injectable()
export class Configuration {

  public Server = 'https://studapp.it.p.lodz.pl:8404/ssbd201804'; // prod
  // public Server = 'https://localhost:8181/ssbd201804';
  // public Server = ''; // npm start

  public ApiUrl = '/api';
  public ServerWithApiUrl = this.Server + this.ApiUrl;
  public ROOT = '/';

  // Report response codes
  public REPORT_SENT = 'report_already_sent';
  public REPORT_CHANGED = 'error_performance_report_optimistic_lock';

  // Points response codes
  public ERROR_COLLEAGUE_VALIDATION_FAILED = 'colleague_validation_failed';
  public ERROR_SUBORDINATE_VALIDATION_FAILED = 'subordinate_validation_failed';
  public ERROR_NOT_ENOUGH_POINTS = 'not_enough_points';

  // Performance report
  public ERROR_BENEFIT_OPTIMISTIC_LOCK = 'error_benefit_optimistic_lock';
  public SUBORDINATES_REPORTS_URL = this.ServerWithApiUrl + '/reports/subordinates';
  public REPORT_EDIT_URL = this.ServerWithApiUrl + '/reports/edit';
  public REPORT_SUBMIT_URL = this.ServerWithApiUrl + '/reports/submit';

  // User response codes
  public ERROR_EMAIL_TAKEN = 'email_taken';
  public ERROR_USERNAME_TAKEN = 'un_taken';
  public SUCCESS = 'success';

  // registration types
  public REGISTRATION_BY_USER = 'r';
  public REGISTRATION_BY_ADMIN = 'a';

  // Pages
  public PAGE_REGISTRATION_SUCCESS = '/registrationSuccessful';
  public PAGE_BENEFIT = '/bmanager/benefits';
  public PAGE_PROPOSED_BENEFIT = '/bmanager/proposedBenefits';
  public PURCHASE_URL = '/employee/purchases';

  // Benefit response code
  public NOT_ENOUGH_BENEFITS = 'not_enough_benefits';
  public ERROR_NO_OBJECT_IN_DATABASE = 'error_no_object_in_database';
}
