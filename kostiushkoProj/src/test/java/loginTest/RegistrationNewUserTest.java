package loginTest;


import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class RegistrationNewUserTest extends BaseTest {

    @Test
    public void checkValidMessageWhenRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationUserNameField("tr");
        loginPage.enterEmailIntoRegistrationEmailField("test.com");
        loginPage.enterPasswordIntoRegistrationPasswordField("123");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginPage.getMessageListWithXpath("Username must be at least 3 characters.");
        loginPage.getMessageListWithXpath("You must provide a valid email address.");
        loginPage.getMessageListWithXpath("Password must be at least 12 characters.");
        Assert.assertEquals("3",3, loginPage.countingTheNumberOfElements());
    }


    }


