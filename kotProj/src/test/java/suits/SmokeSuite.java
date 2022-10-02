package suits;


import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class
})
public class SmokeSuite {

}
