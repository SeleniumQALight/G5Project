package StepDefinitions;

import Pages.HomePage;
import Pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;

public class LoginPage_StepDefinition {

    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_login_page(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String username){
       loginPage.enterUserNameIntoLoginInput(username);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password){
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
       loginPage.clickOnLoginButton();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message){
        loginPage.checkMessageAlert(message);
    }

    @Then("^User is logged in$")
    public void user_is_logged_in() {
        homePage.getHeaderElements().isButtonSignOutDisplayed();
    }

    @When("^User enters '(.*)' username into 'Username' register input on 'Login' page$")
    public void user_enters_fakeUsername_username_into_Username_register_input_on_Login_page(String username){
        loginPage.fillInUserNameRegister(username);
    }

    @When("^User enter '(.*)' email into 'Email' register input on 'Login' page$")
    public void user_enter_email_gmail_com_email_into_Email_register_input_on_Login_page(String email) {
        loginPage.fillInUserEmailRegister(email);
    }

    @When("^User enter '(.*)' password into 'Password' register input on 'Login' page$")
    public void user_enter_password_into_Password_register_input_on_Login_page(String password) {
        loginPage.fillInUserPasswordRegister(password);
    }

    @Then("^Validation errors '(.*)' appears$")
    public void validation_errors_appears(String errorMessage) {
        loginPage.checkErrorMessages(errorMessage);
    }
}
