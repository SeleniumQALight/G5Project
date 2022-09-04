package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

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
        try {
            WebElement buttonSingout = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingout.isDisplayed();

        }catch (Exception e){
            return false;
        }
    }
}

