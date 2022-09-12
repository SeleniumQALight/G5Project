package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[text() = 'Create Post']")
    private WebElement buttonCreatePost;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * клік на кнопку створення поста
     * і повертаємо пейджу створення поста
     */
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
