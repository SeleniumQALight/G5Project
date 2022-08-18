package LoginTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;


public class Logintest {
    WebDriver webDriver;
    @Test
    public void validLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get ("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        WebElement inputlogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputlogin.clear();
        inputlogin.sendKeys("qaauto");
        System.out.println("qaauto was input into login");

        WebElement inputPassword = webDriver.findElement (By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inout");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("sign in was clicked");

        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue ("sign out is not visible",buttonSignOut.isDisplayed());

        webDriver.quit();
        System.out.println("Browser was closed");


    }
    private boolean isButtonSignOutDisplayed() {

        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text]()='Sign out'"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e)

        {
            return false;
        }
    }


}
