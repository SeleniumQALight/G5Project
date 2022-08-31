package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    //метод перевірки чи потрапили ми на потрібну сторінку через ассерт
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();   //перевірка, що чат зник, а потім вже, що сторінка загрузилась
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

}
