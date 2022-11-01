package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class Registration_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @When("^User enters '(.*)' into 'Username' input on 'Registration' form$")
    public void user_enters_username_into_Username_input_on_registration_form(String username) {
        loginPage.enterUsernameIntoRegistrationInput(username);
    }

    @When("^User enters '(.*)' into 'Email' input on 'Registration' form$")
    public void user_enters_email_into_email_input_on_registration_form(String email) {
        loginPage.enterEmailIntoRegistrationInput(email);
    }

    @When("^User enters '(.*)' into 'Password' input on 'Registration' form$")
    public void user_enters_password_into_password_input_on_registration_form(String password) {
        loginPage.enterPasswordIntoRegistrationInput(password);
    }

    @Then("^User sees alert message\\(s\\) with text '(.*)'$")
    public void user_sees_alert_message_with_text(String errorMessage) {
        loginPage.checkErrorsMessagesOnRegistrationForm(errorMessage);
    }
}
