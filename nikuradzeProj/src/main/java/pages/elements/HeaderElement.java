package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * клик на кнопку "створення поста"
     * и повертаемо пейджу Створення поста
     * @return
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
    public MyProfilePage clickOnMyProfileButton( ){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    @Step
    public LoginPage clickOnSignOutButton( ){
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

}
