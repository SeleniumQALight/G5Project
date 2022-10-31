package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class UserRegistration_StepDefinition {

    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page register section$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page_register_section(String username) {
        loginPage.enterUserNameIntoRegistrationInput(username);
    }

    @When("^User enter '(.*)' email into 'Email' input$")
    public void user_enter_email_email_into_Email_input(String email) {
        loginPage.enterEmailIntoRegistrationInput(email);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page register section$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page_register_section(String pass) {
        loginPage.enterUserPasswordIntoRegistrationInput(pass);
    }

    @When("^User click on 'SingUp' button on 'Login' page$")
    public void user_click_on_SingUp_button_on_Login_page() {
        loginPage.clickOnButtonSignUp();
    }

    @Then("^User sees error message with '(.*)' text$")
    public void user_Sees_Error_Message_With_Error_Message_Text_Text(String expectedErrors) {
        loginPage.checkErrorMessages(expectedErrors);
    }
}
