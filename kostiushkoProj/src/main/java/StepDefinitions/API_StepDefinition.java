package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinition {

    final String DEFAULT = "default";
ApiHelper apiHelper = new ApiHelper();
    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
public void createNewPostsViaApiForUser(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)) ;
        {
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i <numberOfPosts ; i++) {
            apiHelper.createPost(" Post from API " + i, userName, password);
        }
    }
}
