package suits;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import postsTests.CreatePostTest;
import registrationTest.RegistrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        RegistrationTest.class
})
public class SmokeSuiteWithCategories {
}
