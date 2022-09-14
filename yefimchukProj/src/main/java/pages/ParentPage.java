package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionsWithElements {
    protected String baseURL;
    //.//*[@name='title']
    @FindBy(name = "title")
    WebElement inputTitle;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseURL = "https://qa-complex-app-for-testing.herokuapp.com";
    }
    abstract String getRelativeURL();
    protected void checkURL() {
        Assert.assertEquals("Invalid page ", baseURL + getRelativeURL(), webDriver.getCurrentUrl());
    }
    protected void checkURLWithPattern(){
        logger.debug(webDriver.getCurrentUrl()); // info is changed to debug in file: log4j.properties
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(),containsString(baseURL + getRelativeURL()));
    }
    protected void waitChatToBeHide() {
        webDriverWait10.withMessage("Chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));

    }
}

