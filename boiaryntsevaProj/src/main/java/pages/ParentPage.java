package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParentPage extends CommonActionsWithElements {

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void waitChatToBeHidden() {
        webDriverWait10.withMessage("Chatbis not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
