package Pages.Elements;

import Pages.CommonActionsWithElements;
import Pages.CreatePostPage;
import Pages.MyProfilePage;
import Pages.PostPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    //створюємо кнопку створення поста і клікаємо на неї і повертаємо сторінку Create Page
    public CreatePostPage clickOnButtonCreatePOst() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }



    public boolean isButtonSignOutDisplayed() {

        return isElementDisplayed(buttonSignOut);
    }


}


