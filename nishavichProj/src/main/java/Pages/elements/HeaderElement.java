package Pages.elements;

import Pages.Commonactions;
import Pages.CreatePostPage;
import Pages.MyProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElement extends Commonactions {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy (xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    /** клік на кнопку створення поста*/
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);

    }

    public MyProfilePage clickOnMyProfileButton (){
        clickOnElement (buttonMyProfile);
        return new MyProfilePage (webDriver);

    }
}

