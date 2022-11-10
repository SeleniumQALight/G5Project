package StepDefinitions;

import Pages.HomePage;
import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.DriverHelper;
import libs.TestData;

public class API_StepDefinition {
    ApiHelper apiHelper = new ApiHelper();
    final String DEFAULT = "default";

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaApiForUSer(int numberOfPost, String userName, String password){
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPost; i++) {
            apiHelper.createPost("Post from API " + i,userName, password);
        }

    }
}
