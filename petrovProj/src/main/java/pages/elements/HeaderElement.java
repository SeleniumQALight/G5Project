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

    @FindBy(xpath = ".//a//img[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement avatarMyProfile;




    public HeaderElement(WebDriver webDriver) {
        super(webDriver);

    }

    /**
     * клин на кнопку створення поста
     * и повертаемо пейдж створення пейджа
     * @return
     */
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public WebElement getAvatarMyProfile() {
        return avatarMyProfile;
    }

    public boolean isButtonSignOutDisplayed(){
        return isElementWasDisplayed(buttonSingOut);
    }
}