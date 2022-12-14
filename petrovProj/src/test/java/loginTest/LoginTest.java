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
    public void validLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sing In was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        }catch (Exception e){
            return false;
        }

    }

    @Test
    public void inValidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto1");
        System.out.println("inValid login - qaauto1 was inputted into login input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password 123456qwerty was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sing In was clicked");

        Assert.assertTrue("Button Sign Out is visible", isMessageErrorLoginOrPassword());

        System.out.println("InValid test passed");
        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isMessageErrorLoginOrPassword(){
        try{
            WebElement messageErrorLoginOrPassword = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return messageErrorLoginOrPassword.isDisplayed();
        }catch (Exception e){
            return false;
        }

    }


}