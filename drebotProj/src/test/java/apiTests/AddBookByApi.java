package apiTests;

import api.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddBookByApi {

    ApiHelperBook apiHelperBook = new ApiHelperBook();

    @Before
    public void precondition() {

        apiHelperBook.deleteAllBooksByUser();

    }

    @Test
    public void addBookToProfileByUser() {
        UserInfoDTO userInfoDTO = apiHelperBook.getUserInfoAfterLogin();

        String token = userInfoDTO.getToken();
        String userId = userInfoDTO.getUserId();

        BooksDTO listOfBooks = apiHelperBook.getAllBooks(token);

        String isbn = listOfBooks.getBooks()[0].getIsbn();

        apiHelperBook.addBookToUser(userId, token, isbn);

        ProfileDTO profileDTO = apiHelperBook.getProfileInfo(userId, token);

        Assert.assertEquals("count of books ", 1, profileDTO.getBooks().length);
        Assert.assertEquals("user name  ", isbn, profileDTO.getBooks()[0].getIsbn());


    }

}
