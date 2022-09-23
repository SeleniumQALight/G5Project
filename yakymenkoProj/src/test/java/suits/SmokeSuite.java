package suits;

import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pages.CreatePostPage;
import postTest.CreatePostTest;

// клас у якому ми хочемо запустити такі класи:
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        LoginTestWithPageObject.class,
        CreatePostTest.class
})
public class SmokeSuite {
}
