package Pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionsWithElements{
protected String baseUrl;




    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("[env]","qa"));   //change environment from qa to ...

    }
abstract String getRelatedUrl();
    protected void checkUrl(){
        Assert.assertEquals("Invalid Page",
                baseUrl + getRelatedUrl(),
                webDriver.getCurrentUrl());

    }

    protected void checkUrlWithPattern(){
        logger.debug(webDriver.getCurrentUrl());
        Assert.assertThat("Invalid Page",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelatedUrl() ));

    }
protected void waitChatToBeHide(){
      webDriverWaitLow.withMessage("Chat is not closed").
              until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
              ".//*[@id='chat-wrapper']")));
}

}