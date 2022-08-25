package Pages.Elements;

import Pages.CommonActionsWithElements;
import Pages.CreatePostPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class HeaderElement extends CommonActionsWithElements {
@FindBy(xpath = ".//a[text()='Create Post']")
private WebElement buttonCreatePost;

public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    //створюємо кнопку створення поста і клікаємо на неї і повертаємо сторінку Create Page
    public CreatePostPage clickOnButtonCreatePOst(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
