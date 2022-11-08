package apiTests;

import api.demoqaBookStoreHW2.AllBooksDTO;
import api.demoqaBookStoreHW2.ApiHelperBookStore;
import api.demoqaBookStoreHW2.UserBooksDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApiTestsForDemoqaBookStoreHW2 {
    ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();

    @Before
    public void loginAndClearListOfBooks() {
        apiHelperBookStore.login();
        apiHelperBookStore.deleteAllBooksInUserList();
    }

    @Test
    public void addBookToUserList() {
        AllBooksDTO booksList = apiHelperBookStore.getAllBooksInStore();
        String firstBookIsbn = booksList.getBooks()[0].getIsbn();

        apiHelperBookStore.addBooksToUserList(firstBookIsbn);
        UserBooksDTO usersList = apiHelperBookStore.getUsersListOfBooks();

        Assert.assertTrue("New book does not displays in user's book-list", (usersList.getBooks().length == 1));
        Assert.assertEquals("Added book contains different isbn", usersList.getBooks()[0].getIsbn(), firstBookIsbn);
    }
}