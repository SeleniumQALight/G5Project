package StepDefinitions;

import api.ApiHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;

public class Hooks {

    ApiHelper apiHelper = new ApiHelper();
    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before(order = 0)
    public void setUp(Scenario scenario){
        driverHelper.createWebDriver();
        logger.info(scenario.getName() + " was started");
    }

    @After(order = 0)
    public void tearDown(Scenario scenario){
        driverHelper.closeWebDriver();
        logger.info(scenario.getName() + " was ended with status " + scenario.getStatus());
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deleteAllPostsForDefaultUser(){
        apiHelper.deletePostTillPresent(TestData.VALID_LOGIN.toLowerCase(), TestData.VALID_PASSWORD);
    }



}
