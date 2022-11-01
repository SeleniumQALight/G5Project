package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.LoginPage;

public class RegistrationPage_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User enters data '(.*)' to 'Username' field on 'Registration' form$")
    public void user_enters_data_use_to_Username_field_on_registration_form(String username) {
        loginPage.enterUserNameIntoRegistrationForm(username);
    }

    @Given("^User enters data '(.*)' to 'Email' field on 'Registration' form$")
    public void user_enters_data_to_Email_field_on_registration_form(String email) {
        loginPage.enterEmailIntoRegistrationFrom(email);
    }

    @Given("^User enters data '(.*)' to 'Password' field on 'Registration' form$")
    public void user_enters_data_to_Password_field_on_registration_form(String pass) {
        loginPage.enterPasswordIntoRegistrationForm(pass);
    }

    @Then("^User sees error message to 'Username' field '(.*)'$")
    public void user_sees_error_message_to_Username_field_Username_must_be_at_least_characters(String message) {
        loginPage.checkNameErrorMessage(message);
    }

    @Then("^User sees error message to 'Email' field '(.*)'$")
    public void user_sees_error_message_to_Email_field_You_must_provide_a_valid_email_address(String message) {
        loginPage.checkEmailErrorMessage(message);
    }

    @Then("^User sees error message to 'Password' field '(.*)'$")
    public void user_sees_error_message_to_Password_field_Password_must_be_at_least_characters(String message) {
        loginPage.checkPasswordMessage(message);
    }

    @Given("^User click submit button 'Sign up for OurApp' on 'Registration' form$")
    public void user_click_submit_button_Sign_up_for_OurApp_on_registration_form() {
        loginPage.clickButtonSignUp();
    }

    @Then("^User sees error message with text '(.*)'$")
    public void user_sees_error_message_with_text(String errorMessage) {
        loginPage.checkErrorsMessages(errorMessage);
    }

}
