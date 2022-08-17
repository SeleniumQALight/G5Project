package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;
    ChromeOptions options;


    @Test
    public void validLogin(){

        WebDriverManager.chromedriver().setup();//set up driver

        options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("start-maximized");
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site Opened");

        WebElement inputLogin = driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
                inputLogin.clear();
                inputLogin.sendKeys("rosko48");
                System.out.println("Login filed was filled in with value = 'rosko48'");

        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']"));
                inputPassword.sendKeys("12345678912345");
                System.out.println("Password filed was filled in with value = '12345678912345'");

        WebElement signInButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
                signInButton.submit();

        //WebElement singOutButton = driver.findElement(By.xpath("//button[@class='btn btn-sm btn-secondary']"));
        Assert.assertTrue("Button 'Sign Out' is not displayed",isButtonSignOutDisplayed());

        driver.quit();
        System.out.println("Browser Closed");
    }


    @Test
    public void invalidLogin(){

        WebDriverManager.chromedriver().setup();//set up driver

        options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("start-maximized");
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site Opened");

        WebElement inputLogin = driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("rosko48");
        System.out.println("Login filed was filled in with value = 'rosko48'");

        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']"));
        inputPassword.sendKeys("wrongPassword");
        System.out.println("Password filed was filled in with value = 'wrongPassword'");

        WebElement signInButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
        signInButton.submit();

        Assert.assertTrue("Validation message or 'Sign In' button is not displayed ",isButtonAndMessagePresent());

        driver.quit();
        System.out.println("Browser Closed");
    }

    private boolean isButtonSignOutDisplayed(){
        try {
            WebElement singOutButton = driver.findElement(By.xpath("//button[@class='btn btn-sm btn-secondary']"));
            return singOutButton.isDisplayed();
        }catch (Exception e){
            System.out.println("Button 'Sight Out' is not found");
            return false;
        }
    }

    private boolean isButtonSignInDisplayed(){
        try {
            WebElement singInButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
            return singInButton.isDisplayed();
        }catch (Exception e){
            System.out.println("Button 'Sight Out' is not found");
            return false;
        }
    }

    private boolean isValidationErrorMessageDisplayed(){
        try{
            WebElement validationMessage = driver.findElement(By.xpath("//div[text()='Invalid username / pasword']"));
            return validationMessage.isDisplayed();
        }catch (Exception e){
            System.out.println("Validation message is not found");
            return false;

        }
    }

    private boolean isButtonAndMessagePresent(){
        if (isButtonSignInDisplayed() && isValidationErrorMessageDisplayed()){
            return true;
        }else{return  false;}
    }
}
