package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("\"qaauto\" was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("\"123456qwerty\" was inputted into password input");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in button was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("\"qaauto\" was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("654321qwerty");
        System.out.println("\"654321qwerty\" was inputted into password input");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in button was clicked");

        Assert.assertTrue("Alert about wrong username/password is not visible", isAlertDisplayed());
        Assert.assertTrue("Button \"Sign In\" is not visible", isButtonSignInDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isAlertDisplayed() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isButtonSignInDisplayed() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
