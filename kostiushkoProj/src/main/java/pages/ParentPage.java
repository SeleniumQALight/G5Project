package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);

        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("env", "qa"));
    }


    abstract String getRelateUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page"
              ,baseUrl + getRelateUrl()
              ,webDriver.getCurrentUrl());
    }

    protected void checkUrlwithPattern(){
        Assert.assertThat("Invalid page"
                ,webDriver.getCurrentUrl()
                ,containsString(baseUrl+getRelateUrl()));
    }


    protected void waitChatToBeHide() {
        webDriverWaitLow.withMessage("Chat is not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));

    }

}
