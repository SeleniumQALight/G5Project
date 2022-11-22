package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class RegistrationPage_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @When("^User enters '(.*)' into 'Username' input on 'Registration' form$")
    public void user_enters_ol_into_Username_input_on_Registration_form(String username) {
        loginPage.enterUsernameIntoUsernameRegisterInput(username);
    }

    @When("^User enters '(.*)' into 'Email' input on 'Registration' form$")
    public void user_enters_test_com_ua_into_Email_input_on_Registration_form(String email) {
        loginPage.enterEmailIntoEmailRegisterInput(email);
    }

    @When("^User enters '(.*)' into 'Password' input on 'Registration' form$")
    public void user_enters_qwerty_into_Password_input_on_Registration_form(String password) {
        loginPage.enterPasswordIntoPasswordRegisterInput(password);
    }

    @Then("^User sees alert message\\(s\\) with text '(.*)'$")
    public void user_sees_alert_message_s_with_text_Username_must_be_at_least_characters(String errorMessage) {
        loginPage.checkErrorsMessagesOnRegistrationForm(errorMessage);
    }
}