package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@class='mr-2']")
    private WebElement buttonMyProfile;


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

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}

