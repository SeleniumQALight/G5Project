package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath=".//img[@class=\"avatar-small\"]")
    private WebElement avatar;
    private String postTitleLocator=".//*[text()='%s']";
    public MyProfilePage(WebDriver webDriver){ super(webDriver);}


  //  public MyProfilePage checkPostWasCreated(String title){
//        List<>
 //   }
}
