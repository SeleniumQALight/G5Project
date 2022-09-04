package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParentPage extends CommonActionWithElements{

    public ParentPage(WebDriver driver) {
        super(driver);
    }

    protected void waitChatToBeHide(){
        webDriverWait10.withMessage("Chat is not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath("//div[@id='chat-wrapper']")));
    }
}
