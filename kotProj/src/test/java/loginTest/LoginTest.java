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

        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        System.out.println("qaauto was typed into username");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("Password was typed");

        webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
        System.out.println("Sign in was clicked");

//WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Button Sign out is not visible ", isButtonSignOutDisplayed());

        webDriver.quit();
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


    @Test
    public void invalidLogin(){
       WebDriverManager.chromedriver().setup();
       webDriver = new ChromeDriver();
       webDriver.manage().window().maximize();
       webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
       webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
       System.out.println("Site opened");

       WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
       inputLogin.clear();
       inputLogin.sendKeys("qaauto");
        System.out.println("Username is typed");


        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("Invalidpassword");
        System.out.println("Invalid Password was typed");


        webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
        System.out.println("Sign in was clicked");

    WebElement alertInvalidpassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
        System.out.println("Alert Invalid password appears");
     Assert.assertTrue("Alert about invalid password does not appear", doesAlertInvalidPasswordAppear());


        webDriver.quit();
        System.out.println("Browser was closed");


    }

private boolean doesAlertInvalidPasswordAppear(){
        try {
            WebElement alertInvalidpassword = webDriver.findElement(By.xpath
                    (".//div[@class='alert alert-danger text-center']"));
            return alertInvalidpassword.isDisplayed();
        }catch (Exception e){
            return false;
        }
}

}
