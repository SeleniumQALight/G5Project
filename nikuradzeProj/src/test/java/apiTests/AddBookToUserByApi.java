package apiTests;

import api.ApiHelperBooks;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AddBookToUserByApi {
    Logger logger = Logger.getLogger(getClass());
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();

    @Before
    public void deleteAllBooksForUser(){
        apiHelperBooks.login();
        apiHelperBooks.deleteBooks();
    }

    @Test
    public void addBookToUser(){
        String firstBookIsbn = apiHelperBooks.getAllBooksFromStore().getBooks()[0].getIsbn();

        apiHelperBooks.addBooksToUser(firstBookIsbn);

        Assert.assertTrue("Number of books added by user is ", (apiHelperBooks.getAllBooksByUser().getBooks().length==1)
                && (apiHelperBooks.getAllBooksByUser().getBooks()[0].getIsbn().equals(firstBookIsbn)));
        logger.info(String.format("Book with isbn %s is placed in user's library", firstBookIsbn));
    }
}
