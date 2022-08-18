package negativePasswordTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class NegativePasswordTest {
    WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into login input");

        WebElement inputNegativePassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputNegativePassword.clear();
        inputNegativePassword.sendKeys("123456qwerty123456");
        System.out.println("password was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign In was clicked");

        Assert.assertTrue("Message 'Invalid username / password' is not visible", isAMessageInvalidUsernamePassword());

        webDriver.quit();
        System.out.println("Browser was closet");
    }

    private boolean isAMessageInvalidUsernamePassword(){
        try {
            WebElement message = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username / password')]"));
            return message.isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }
}
