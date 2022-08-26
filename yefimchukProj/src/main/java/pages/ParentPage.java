package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPage extends CommonActionsWithElements {
    //.//*[@name='title']
    @FindBy(name = "title")
    WebElement inputTitle;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}

