package baseTest;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.LoginPage;

public class BaseTest {
    WebDriver webDriver;
    Logger  logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before
    public void setUp(){
        logger.info("------"+testName.getMethodName()+" was started-------");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage= new HomePage(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("------"+testName.getMethodName()+" was ended-------");
    }
    @Rule
    public TestName testName=new TestName();

}
