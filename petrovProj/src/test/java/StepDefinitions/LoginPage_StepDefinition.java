package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User open 'Login' page$")
    public void user_open_login_page(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName){
        loginPage.enterUserNameIntoLoginInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password){
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page(){
        loginPage.clickOnButtonLogin();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message){
       loginPage.checkAlertMessageText(message);
    }

    @And("^User click on 'SingIn' button on 'Login' page and redirect to Home page$")
    public void userClickOnSingInButtonOnLoginPageAndRedirectToHomePage() {
        loginPage.clickOnButtonLogin();
    }

    @When("^User enters '(.*)' username into 'Pick a username' input on 'Login' page$")
    public void userEntersUserNameUsernameIntoPickAUsernameInputOnLoginPage(String username) {
        loginPage.enterUserNameIntoRegistrationForm(username);
    }

    @And("^User enters '(.*)' into 'email' input on 'Login' page$")
    public void userEntersEmailIntoEmailInputOnLoginPage(String email) {
        loginPage.enterEmailIntoRegistrationForm(email);

    }

    @And("^User enters '(.*)' password into 'password' input on 'Login' page$")
    public void userEntersPassPasswordIntoPasswordInputOnLoginPage(String password) {
        loginPage.enterPasswordIntoRegistrationForm(password);

    }

    @Then("^Check '(.*)' on 'Login' page$")
    public void checkErrorMessageOnLoginPage(String errorMessages) {
        loginPage.checkErrorsMessage(errorMessages);

    }


}
