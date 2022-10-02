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
        WebElement inputLogin = webDriver.findElement(By.xpath(".//input [@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was input into login input");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input [@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("123456qwerty was input into login input");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("Browser was clossed");
    }
    @Test
    public void noValidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        WebElement inputLogin = webDriver.findElement(By.xpath(".//input [@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was input into login input");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input [@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("noValidLPassword");
        System.out.println("noValidLPassword was input into login input");
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign in was clicked");
        webDriver.findElement(By.xpath(".//* [@class='alert alert-danger text-center']"));
        Assert.assertTrue("Tetx 'Invalid username / pasword' is not visible", isTextNoLogginDisplayed());

        webDriver.quit();
        System.out.println("Browser was clossed");
    }


    private boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTextNoLogginDisplayed() {
        try {
            WebElement textNoLoggin = webDriver.findElement(By.xpath(".//* [@class='alert alert-danger text-center']"));
            return textNoLoggin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
