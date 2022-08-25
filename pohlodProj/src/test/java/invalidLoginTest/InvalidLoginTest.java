package invalidLoginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InvalidLoginTest {
    WebDriver webDriver;
    @Test
    public void invalidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into the login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456");
        System.out.println("Invalid password was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign In was clicked");

        webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
        Assert.assertTrue("Text is found", isTextDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");

    }

    private boolean isTextDisplayed(){
        try{
            WebElement invalidPasswordLoginMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return invalidPasswordLoginMessage.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
