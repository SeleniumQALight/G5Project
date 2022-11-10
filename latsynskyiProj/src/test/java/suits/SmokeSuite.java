package suits;

import apiTests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import loginTest.LoginTestWithPageObject;
import postsTests.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
