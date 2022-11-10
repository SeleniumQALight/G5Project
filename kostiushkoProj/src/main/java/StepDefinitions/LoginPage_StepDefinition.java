package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {
LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    @Given("^User opens 'Login' page$")
    public void user_opens_login_page (){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) throws Throwable {
        loginPage.enterUserNameIntoLogininInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password)  {
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
    loginPage.clickOnButtonLogIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String message)  {
    loginPage.checkMessageText(message);
    }

    @When("^User enters '(.*)' username into 'Username' input on 'Registration' form on 'Login' page$")
    public void userEntersUsernameUsernameIntoUsernameInputOnRegistrationFormOnLoginPage(String username) {
    loginPage.enterUserNameIntoRegistrationForm(username);
    }

    @And("^User enters '(.*)' email into 'Email' input on 'Registration' form on 'Login' page$")
    public void userEntersEmailEmailIntoEmailInputOnRegistrationFormOnLoginPage(String email) {
        loginPage.enterEmailIntoRegistrationForm(email);
    }

    @And("^User enters '(.*)' password into 'Password' input on 'Registration' form on 'Login' page$")
    public void userEntersPasswordPasswordIntoPasswordInputOnRegistrationFormOnLoginPage(String password) {
        loginPage.enterPasseordIntoRegistrationForm(password);
    }

    @Then("^User sees error alert message with text '(.*)'$")
    public void userSeesAlertMessageSWithTextErrorMessageS(String expectedErrors) {
        loginPage.chekErrorsMessages(expectedErrors);
    }
}
