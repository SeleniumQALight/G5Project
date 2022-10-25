package apiTests;

import api.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QAToolsBooksAdditional {
    public String addedBookIsbn = "";

    Logger logger = Logger.getLogger(getClass());
    QAToolsApiHelper apiHelper = new QAToolsApiHelper();

    @Before
    public void userLogin() {
        apiHelper.login();
    }

    @Test
    public void addNewBookToUsersList() {
        QAToolsBooksDTO[] userBookList = apiHelper.getUsersListOfBooks().getBooks();
        QAToolsBooksDTO[] storeBookList = apiHelper.getAllBooksInStore().getBooks();
        ArrayList<String> usersIsbnArray = apiHelper.isbnParser(userBookList);
        ArrayList<String> storeBooksIsbnArray = apiHelper.isbnParser(storeBookList);

        storeBooksIsbnArray.removeAll(usersIsbnArray);

        if (storeBooksIsbnArray.size() == 0) {
            logger.info("All books in store already added to user collection");
        } else {
            addedBookIsbn = storeBooksIsbnArray.get(0);
            apiHelper.addBooksToUserList(addedBookIsbn);
        }
    }

    @After
    public void deleteAddedBookFromUsersList() {
        if (!addedBookIsbn.equals("")) {
            apiHelper.deleteBookInUserListByIsbn(addedBookIsbn);
        }
    }
}
