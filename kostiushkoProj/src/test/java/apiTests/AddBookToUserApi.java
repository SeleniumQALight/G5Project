package apiTests;

import api.ApiHelperDemoqa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddBookToUserApi {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();

    @Before
    public void deleteAllBooksForUser(){
        apiHelperDemoqa.login();
        apiHelperDemoqa.deleteBooks();
    }

    @Test
    public void addBookToUser(){
        String bookIsbn = apiHelperDemoqa.getAllBooksFromStore().getBooks()[0].getIsbn();

        apiHelperDemoqa.addBooksToUser(apiHelperDemoqa.getAllBooksFromStore().getBooks()[0].getIsbn());

        Assert.assertTrue("Number of books added by user ", (apiHelperDemoqa.getAllBooksByUser().getBooks().length==1)
                && (apiHelperDemoqa.getAllBooksByUser().getBooks()[0].getIsbn().equals(bookIsbn)));
    }
}

