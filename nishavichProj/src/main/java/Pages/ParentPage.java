package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParentPage extends Commonactions {


    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    protected void waitChatToBeHide(){
        webDriverWait10.withMessage("chat is not closed")
                .until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));

    }
}


