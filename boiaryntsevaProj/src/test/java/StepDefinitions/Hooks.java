package StepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;

public class Hooks {
    DriverHelper driverHelper = new DriverHelper();

    @Before
    public void setUo() {
        driverHelper.createWebDriver();
    }

    @After
    public void tearDown() {
        driverHelper.closeDriver();
    }
}
