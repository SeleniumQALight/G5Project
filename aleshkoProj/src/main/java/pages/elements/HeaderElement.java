package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//form[@action='/logout']//button")
    private WebElement buttonSignOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }
}
