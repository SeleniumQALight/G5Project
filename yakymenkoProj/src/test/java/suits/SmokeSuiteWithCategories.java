package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTest.class,
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        RegistrationTest.class
})
public class SmokeSuiteWithCategories {
}
