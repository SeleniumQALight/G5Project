package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    WebDriver driver;
    Logger log = Logger.getLogger(getClass());

    public ParentPage(WebDriver driver) {
        this.driver = driver;
    }
}
