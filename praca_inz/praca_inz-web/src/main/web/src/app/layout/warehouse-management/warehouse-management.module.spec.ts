import { WarehouseManagementModule } from './warehouse-management.module';

describe('WarehouseManagementModule', () => {
  let warehouseManagementModule: WarehouseManagementModule;

  beforeEach(() => {
    warehouseManagementModule = new WarehouseManagementModule();
  });

  it('should create an instance', () => {
    expect(warehouseManagementModule).toBeTruthy();
  });
});
