package LoginTest;

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
        webDriver.manage().window().maximize(); //Window is max size
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Waiting for 5 second during is opening

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was open");

        WebElement inputLogin =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username' ]"));
        inputLogin.clear();
        inputLogin.sendKeys("Vadim");
        System.out.println("qaauto was inputed into login input");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("12Vadim121212");
        System.out.println("Password was inputed");

        webDriver.findElement(By.xpath(".//button[text() = 'Sign In']")).click();
        System.out.println("Sign in was clicked");



        Assert.assertTrue("Button Sign out is not visible", isButtonSignOutDisplayed());

        webDriver.quit(); // Close browser
        System.out.println("Browser was closed");


    }

    private boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text() = 'Sign Out']"));

            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    @Test
    public void unvalidLogin () {
        WebDriverManager.chromedriver().setup(); // Download exe file.
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site has been  opened");

        WebElement inputLogin =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("Vadi");
        System.out.println("Vadim has been inputed into login input");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("12Vadim121212");
        System.out.println("12Vadim121212 has been inputed into password input");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text() = 'Sign In']"));
        buttonSignIn.click();
        System.out.println("Sign in button has been clicked");



        Assert.assertTrue("User is logged in",messageInvalidUserPassword());

        webDriver.quit();
        System.out.println("Site has been closed");

    }
    private boolean messageInvalidUserPassword () {
        try {
            WebElement textMessageInvalid =
                    webDriver.findElement(By.xpath(".//div[text() = 'Invalid username / pasword']"));

            return textMessageInvalid.isDisplayed();
        }catch (Exception e) {
            return  false;
        }
    }

}
