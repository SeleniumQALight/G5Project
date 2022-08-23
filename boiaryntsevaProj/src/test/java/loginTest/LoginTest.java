package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        WebElement inputLogin = webDriver.findElement(By
                .xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("Qaauto was enterd");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("Pass is sent");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in button is clicked");
// .//button[text()='Sign Out']

        //  WebElement buttonSignOut=webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Sign out Button is not visible", isButtonSignedOutDisplayed());

        webDriver.quit();
        System.out.println("Driver was closed");
    }

    @Test
    public void invalidPasswordLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        WebElement inputLogin = webDriver.findElement(By
                .xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("Qaauto was enterd");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("1123456qwerty");
        System.out.println("Incorrect pass is sent");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in button is clicked");

        //Assert.assertFalse("Sign out Button is visible", isButtonSignedOutDisplayed());
        Assert.assertTrue("Invalid username / password message is not there", isInvalidLoginMessageVisible());

        webDriver.quit();
        System.out.println("Driver was closed");
    }
    private boolean isButtonSignedOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    private boolean isInvalidLoginMessageVisible(){
        try{
            WebElement invalidLoginMessage = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid')]"));
            return invalidLoginMessage.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

}
