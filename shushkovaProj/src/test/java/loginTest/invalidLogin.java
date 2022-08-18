package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class invalidLogin {
    WebDriver webDriver;
    @Test
    public void invalidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver=new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site opened");
        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaaut");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();

        Assert.assertTrue("Message InvalidNameOrPasswordDisplayed ",isMessageInvalidNameOrPasswordDisplayed());

        webDriver.quit();
        System.out.println("Browser closed");

    }
    private boolean isMessageInvalidNameOrPasswordDisplayed(){
        try {
            WebElement messageInvalidNameOrPassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return messageInvalidNameOrPassword.isDisplayed();

        }catch (Exception e){
            return false;
        }
    }

}

