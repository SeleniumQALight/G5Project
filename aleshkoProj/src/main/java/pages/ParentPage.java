package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("env","qa"));
    }

    protected void checkUrl() {
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\n ActualURL: " + actualURL +  "\n " + "ExpectedURL: " +  baseUrl + getRelativeUrl() + " \n "
                , actualURL.matches(baseUrl + getRelativeUrl()));
    }

    protected abstract String getRelativeUrl();

    protected void waitChatToBeHide() {
        webDriverWaitLow.withMessage("Chat is not closed").
                until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@id='chat-wrapper']")));
    }
}