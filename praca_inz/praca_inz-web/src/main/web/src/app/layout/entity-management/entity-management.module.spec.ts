import { EntityManagementModule } from './entity-management.module';

describe('EntityManagementModule', () => {
  let entityManagementModule: EntityManagementModule;

  beforeEach(() => {
    entityManagementModule = new EntityManagementModule();
  });

  it('should create an instance', () => {
    expect(entityManagementModule).toBeTruthy();
  });
});
