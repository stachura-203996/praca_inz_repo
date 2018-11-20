import { CompanyManagementModule } from './company-management.module';

describe('CompanyManagementModule', () => {
  let companyMenagementModule: CompanyManagementModule;

  beforeEach(() => {
    companyMenagementModule = new CompanyManagementModule();
  });

  it('should create an instance', () => {
    expect(companyMenagementModule).toBeTruthy();
  });
});
