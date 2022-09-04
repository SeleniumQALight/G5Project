package Pages.elements;

import Pages.CommonActionWithElements;
import Pages.CreatePostPage;
import Pages.MyProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElements extends CommonActionWithElements {

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//img[@data-toggle='tooltip']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSingIn;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    WebElement singOutButton;

    public HeaderElements(WebDriver driver) {
        super(driver);
    }

    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(driver);
    }

    public MyProfilePage clickOnButtonMyProfile(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(driver);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSingIn);
    }

    public boolean isButtonSignOutDisplayed(){
        return isElementDisplayed(singOutButton);
    }
}
