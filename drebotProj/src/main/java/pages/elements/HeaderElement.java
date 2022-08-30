package pages.elements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
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

