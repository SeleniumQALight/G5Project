package pages.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElement;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionWithElement {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//a//img[@data-original-title='My Profile']")
    WebElement buttonMyProfile;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    WebElement buttonSignOut;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    /**
     * клик на кнопку "Create Post"
     * и возвращаем пейжу Создания поста
     * и возвращаем пейжу Создания поста
     */
    public CreatePostPage clickOnButtonCreatePost () {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public boolean isButtonSighOutDisplayed() {

        return isElementDisplayed(buttonSignOut);
    }
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
