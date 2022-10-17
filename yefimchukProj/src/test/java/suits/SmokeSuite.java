package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreatePostTest;
import registrationTest.RegistrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTest.class,
        CreatePostTest.class,
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class SmokeSuite {
}
