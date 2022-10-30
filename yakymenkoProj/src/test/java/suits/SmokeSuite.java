package suits;

import apiTests.ApiTests;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

// клас у якому ми хочемо запустити такі класи:
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
