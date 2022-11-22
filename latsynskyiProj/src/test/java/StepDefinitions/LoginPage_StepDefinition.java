package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinition {
LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page()
    //throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        loginPage.openLoginPage();
    }
    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName)  {
        loginPage.enterUserNameIntoLoginInput(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_passWord_into_PassWord_input_on_Login_page(String password)  {
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page()  {
        loginPage.clickOnButtonLogIn();

    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String message) {
       loginPage.checkAlertMessageText(message);
    }

}
