import { DeviceManagementModule } from './device-management.module';

describe('DeviceManagementModule', () => {
  let deviceManagementModule: DeviceManagementModule;

  beforeEach(() => {
    deviceManagementModule = new DeviceManagementModule();
  });

  it('should create an instance', () => {
    expect(deviceManagementModule).toBeTruthy();
  });
});
