package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//*[@alt='My profile']")
    private WebElement userAvatar;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }


    /**
     * клік на кнопку "створення поста"
     * і повертаємо пейджу Створення поста
     */
    @Step
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public boolean isButtonSighOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isAvatarDisplayed() {
        return isElementDisplayed(userAvatar);
    }

}
