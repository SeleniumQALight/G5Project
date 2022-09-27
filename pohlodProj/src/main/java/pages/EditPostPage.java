package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name=\"title\"]")
    WebElement inputTitle;

    @FindBy(xpath = ".//button[@class=\"btn btn-primary\"]")
    WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }


    public EditPostPage editTitle(String newTitle) {
        enterTextIntoElement(inputTitle, newTitle);
        return this;
    }

    public PostPage clickOnTheSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }
}


