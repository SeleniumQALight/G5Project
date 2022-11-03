package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {

    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_login_page() {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterUserNameIntoLoginInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password) {
        loginPage.enterPasswordIntoPasswordInput(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonLogIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessagesText(message);
    }

    @When("^User enters '(.*)' username into 'Username' input on 'Login' page registration section$")
    public void user_enters_tr_username_into_Username_input_on_Login_page_registration_section(String userName) {
        loginPage.enterUserNameIntoRegistration(userName);
    }

    @When("^User enters '(.*)' email into 'Email' input on 'Login' page registration section$")
    public void user_enters_test_com_email_into_Email_input_on_Login_page_registration_section(String email) {
        loginPage.enterEmailIntoRegistration(email);
    }

    @When("^User enters '(.*)' password into 'Password' input on 'Login' page registration section$")
    public void user_enters_password_into_Password_input_on_Login_page_registration_section(String password) {
        loginPage.enterPasswordIntoRegistration(password);
    }

    @Then("^User sees alert message with '(.*)' text$")
    public void user_sees_alert_message_with_text(String message) {
        loginPage.checkErrorMessages(message);
    }
}
