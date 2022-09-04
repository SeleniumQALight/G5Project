package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParentPage extends CommonActionsWithElements {
    //.//*[@name='title']
    @FindBy(name = "title")
    WebElement inputTitle;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void waitChatToBeHide() {
        webDriverWait10.withMessage("Chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));

    }
}

