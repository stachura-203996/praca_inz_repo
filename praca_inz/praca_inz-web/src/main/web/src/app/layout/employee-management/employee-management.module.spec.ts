import { EmployeeManagementModule } from './employee-management.module';

describe('EmployeeManagementModule', () => {
  let employeeManagementModule: EmployeeManagementModule;

  beforeEach(() => {
    employeeManagementModule = new EmployeeManagementModule();
  });

  it('should create an instance', () => {
    expect(employeeManagementModule).toBeTruthy();
  });
});
