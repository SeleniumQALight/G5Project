package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = "https://[env]-complex-app-for-testing.herokuapp.com"
                .replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid url"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }
    protected void checkUrlWithPatterns(){
        logger.debug(webDriver.getCurrentUrl());
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                ,containsString(baseUrl+getRelativeUrl()));
    }

    protected void waitChatToBeHidden() {
        webDriverWait10.withMessage("Chatbis not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
