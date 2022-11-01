package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath =".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement avatarMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * клік на кнопку "створити пост"
     * і повертаємо пейджу створення поста
     */
    @Step
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutDisplayed(){
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(avatarMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public  boolean isAvatarMyProfileDisplayed(){
        return isElementDisplayed(avatarMyProfile);

    }
}
