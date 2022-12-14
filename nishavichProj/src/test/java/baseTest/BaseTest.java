package baseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.log4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser wes opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);

    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");

    }
}
