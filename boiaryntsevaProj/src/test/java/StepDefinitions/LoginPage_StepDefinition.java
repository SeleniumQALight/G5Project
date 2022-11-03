package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;
import pages.elements.HeaderElement;

public class LoginPage_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HeaderElement headerElement = new HeaderElement(DriverHelper.getWebDriver());

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
        loginPage.clickOnButtonLogin();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String message) {
        loginPage.checkAlertMessageText(message);
    }

    @Then("^User avatar is displayed$")
    public void user_avatar_is_displayed() {
        headerElement.isAvatarDisplayed();
    }

    @Then("^sign out button is displayed$")
    public void sign_Out_Button_Is_Displayed() {
        headerElement.isButtonSignedOutDisplayed();
    }

}
