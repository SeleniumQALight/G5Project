package pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * клик на кнопку "створення поста"
     * и повертаемо пейджу Створення поста
     * @return
     */
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public boolean isButtonSignOutDisplayed(){
        return isElementDisplayed(buttonSignOut);
    }
}
