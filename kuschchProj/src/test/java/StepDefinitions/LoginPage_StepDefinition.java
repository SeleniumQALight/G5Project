package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class LoginPage_StepDefinition {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterUserNameIntoLoginInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password) {
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonLogIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String message) {
        loginPage.checkAlertMessageText(message);
    }


    @Then("^User sees avatar$")
    public void user_Sees_Avatar() {
        homePage.checkAvatarIsDisplayed();
    }

    @When("^User enters '(.*)' into 'Input username' input on 'Login' page$")
    public void user_enters_login_into_Input_username_input_on_Login_page(String login){
        loginPage.enterUserNameIntoRegistrationForm(login);
    }

    @When("^User enters '(.*)' into 'Input email' input on 'Login' page$")
    public void user_enters_qqq_into_Input_email_input_on_Login_page(String email){
        loginPage.enterEmailIntoRegistrationFrom(email);
    }

    @When("^User enters '(.*)' into 'Input password' input on 'Login' page$")
    public void user_enters_into_Input_password_input_on_Login_page(String password){
        loginPage.enterPasswordIntoRegistrationForm(password);
    }

    @Then("^User sees error messages '(.*)' on 'Login' page$")
    public void user_sees_error_messages_expectedErrors_on_Login_page(String expectedErrors){
        loginPage.checkErrorsMessages(expectedErrors);
    }
}
