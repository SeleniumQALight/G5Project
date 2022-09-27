package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.EditPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

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



    }


