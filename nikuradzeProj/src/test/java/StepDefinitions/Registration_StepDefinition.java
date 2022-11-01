package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class Registration_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @When("^User enters '(.*)' username into 'Username' input on 'Registration' form on 'Login' page$")
    public void user_enters_username_into_Username_input_on_Registration_form_on_Login_page(String username) {
        loginPage.enterUserNameIntoRegistrationInput(username);
    }

    @When("^User enters '(.*)' email into 'Email' input on 'Registration' form on 'Login' page$")
    public void user_enters_mail_email_into_Email_input_on_Registration_form_on_Login_page(String email) {
        loginPage.enterEmailIntoRegistrationInput(email);
    }

    @When("^User enters '(.*)' password into 'Password' input on 'Registration' form on 'Login' page$")
    public void user_enters_password_into_Password_input_on_Registration_form_on_Login_page(String password) {
        loginPage.enterPasswordIntoRegistrationInput(password);
    }

    @Then("^User sees alert message\\(s\\) with text '(.*)'$")
    public void user_sees_alert_message_s_with_text_Error_message_s(String expectedErrors) {
        loginPage.checkErrorsMessages(expectedErrors);
    }
}
