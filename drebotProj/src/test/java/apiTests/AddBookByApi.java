package apiTests;

import api.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddBookByApi {
    String token = "";
    String userId = "";

    ApiHelperBook apiHelperBook = new ApiHelperBook();

    @Before
    public void precondition() {
        UserInfoDTO userInfoDTO = apiHelperBook.getUserInfoAfterLogin();

        userId = userInfoDTO.getUserId();
        token = userInfoDTO.getToken();

        apiHelperBook.deleteAllBooksByUser(userId, token);

    }

    @Test
    public void addBookToProfileByUser() {

        BooksDTO listOfBooks = apiHelperBook.getAllBooks();

        String isbn = listOfBooks.getBooks()[0].getIsbn();

        apiHelperBook.addBookToUser(userId, token, isbn);

        ProfileDTO profileDTO = apiHelperBook.getProfileInfo(userId, token);

        Assert.assertEquals("count of books ", 1, profileDTO.getBooks().length);
        Assert.assertEquals("user name  ", isbn, profileDTO.getBooks()[0].getIsbn());


    }

}
