import { MainPageModule } from './main-page.module';

describe('MainPageModule', () => {
  let dashboardModule: MainPageModule;

  beforeEach(() => {
    dashboardModule = new MainPageModule();
  });

  it('should create an instance', () => {
    expect(dashboardModule).toBeTruthy();
  });
});
