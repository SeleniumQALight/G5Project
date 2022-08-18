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
        WebDriverManager.chromedriver().setup();//download browser
        webDriver = new ChromeDriver();//chrome
        webDriver.manage().window().maximize();//full size window
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//max 15

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @class='form-control form-control-sm input-dark']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("'qaauto' was inputted into login input ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@name='password' and @class='form-control form-control-sm input-dark']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
        System.out.println("button 'Sign In' was clicked");

        //WebElement buttonSignOut=webDriver.findElement(By.xpath(".//button[@class='btn btn-sm btn-secondary']"));
        Assert.assertTrue("button 'Sign Out' is not visible", isButtonSignOutDisplayed());


        webDriver.quit();//close brouser and delete object
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[@class='btn btn-sm btn-secondary']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
