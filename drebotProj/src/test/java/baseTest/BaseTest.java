package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before//vipolnyaem do anatacii test
    public void setUp() {
        WebDriverManager.chromedriver().setup();//obnovlyaem na poslednuu vesiuchrom driver
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
    }

    @After//posle anatacii test
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }
}