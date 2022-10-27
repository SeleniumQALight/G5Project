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
    public void user_enters_userName_into_Login_input_on_login_page(String userName) {
        loginPage.enterUsernameIntoLoginInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_passWord_input_on_login_page(String password) {
        loginPage.enterPasswordIntoPasswordInput(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_singIn_button_on_login_page() {
        loginPage.clickOnSignInButton();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_invalid_username_password(String alertText) {
        loginPage.checkAlertMessageText(alertText);
    }
}
