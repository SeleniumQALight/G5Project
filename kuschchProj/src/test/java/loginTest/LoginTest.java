package loginTest;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and " +
                "@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was inputted into login input ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty1");
        System.out.println("password was inputted");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Sign In was clicked");

//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Button Sign Out is not visible", isButtonSighOutDisplayed());


        webDriver.quit();
        System.out.println("Browser was closed");

    }

    private boolean isButtonSighOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void test() {
        int[] area = {1, 2, 3, 4};
//        System.out.println(area[1]);

       String line = "1111;2222;3333";
       String[] lineArr = line.split(";");
        System.out.println(lineArr[1]);

        System.out.println(line.replace(";", "-"));

    }
}
