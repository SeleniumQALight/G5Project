package loginTest;

import categories.SmokeTestFilter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    WebDriver webDriver;

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize(); // зробили на весь екран
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // дефолтне очікування

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign In was clicked");

//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutDisplayed()); // Бібліотека яка відповідає за помилки


        webDriver.quit(); // закриває браузер і знищує його як об'єкт
        System.out.println("Browser was closed");

    }

    @Test
    public void validLoginAndInvalidPassword() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty1");
        System.out.println("invalid password 123456qwerty1 was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign In was clicked");

//        WebElement alertInvalidUsernamePassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
        Assert.assertTrue("Alert 'Invalid username / pasword' is not visible", isAlertDisplayed());

        webDriver.quit(); // закриває браузер і знищує його як об'єкт
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isAlertDisplayed() {
        try {
            WebElement alertInvalidUsernamePassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return alertInvalidUsernamePassword.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
