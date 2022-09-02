package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;


    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignedOutDisplayed() {
//        try {
//            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
//            return buttonSignOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(signOutButton);
    }


}
