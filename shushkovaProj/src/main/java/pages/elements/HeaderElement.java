package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    /** клік на кнопку створення поста
      i повертаємо пейджу CreatePostPage **/

    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);

    }
    @Step
    public boolean isButtonSingOutDisplayed(){
            return isElementDisplayed(buttonSingOut);
        }
        public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
        }
    public EditPage clickOnMyProfileButtonOnEditPage(){
        clickOnElement(buttonMyProfile);
        return new EditPage(webDriver);
    }

    public HomePage clickOnButtonSingOut(){
        clickOnElement(buttonSingOut);
        return new HomePage(webDriver);
    }

    public boolean isButtonSingInDisplayed(){
        return isElementDisplayed(buttonSingIn);
    }



    }


