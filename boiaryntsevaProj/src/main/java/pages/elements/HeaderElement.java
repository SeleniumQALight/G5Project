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

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;


    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy (xpath = ".//*[@data-original-title='My Profile']")
    private WebElement avatar;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnSignOutButton(){
        clickOnElement(signOutButton);
        return new LoginPage(webDriver);
    }

    @Step
    public boolean isButtonSignedOutDisplayed() {
//        try {
//            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
//            return buttonSignOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(signOutButton);
    }

    public boolean isAvatarDisplayed(){
        return isElementDisplayed(avatar);
    }


}
