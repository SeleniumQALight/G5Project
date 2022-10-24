package apiTests;

import api.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QAToolsBooks {
    QAToolsApiHelper apiHelper = new QAToolsApiHelper();

    @Before
    public void loginAndClearListOfBooks() {
        apiHelper.login();
        apiHelper.deleteAllBooksInUserList();
    }

    @Test
    public void addBookToUserList() {
        QAToolsBooksListDTO booksList = apiHelper.getAllBooksInStore();
        String firstBookIsbn = booksList.getBooks()[0].getIsbn();

        apiHelper.addBooksToUserList(firstBookIsbn);
        QAToolsUsersBooks usersList = apiHelper.getUsersListOfBooks();

        Assert.assertTrue("New book does not displays in user's book-list", (usersList.getBooks().length == 1));
        Assert.assertEquals("Added book contains different isbn", usersList.getBooks()[0].getIsbn(), firstBookIsbn);
    }
}
