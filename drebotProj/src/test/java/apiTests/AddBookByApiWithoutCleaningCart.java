package apiTests;

import api.*;
import org.junit.Before;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AddBookByApiWithoutCleaningCart {
    ApiHelperBook apiHelperBook = new ApiHelperBook();

    Logger logger = Logger.getLogger(getClass());

    String isbnBook = "";
    String token = "";
    String userId = "";

    @Before
    public void precondition() {
        UserInfoDTO userInfoDTO = apiHelperBook.getUserInfoAfterLogin();

        userId = userInfoDTO.getUserId();
        token = userInfoDTO.getToken();

    }


    @Test
    public void addBookToProfileByUserWithoutCleanProfile() {

        ProfileDTO profileDTO = apiHelperBook.getProfileInfo(userId, token);

        BookDTO[] userBook = profileDTO.getBooks();
        Integer beginningSizeOfUsersCart = userBook.length;
        List<String> listOfUserIsbn = new ArrayList<>();

        BooksDTO listOfAllBooks = apiHelperBook.getAllBooks();
        BookDTO[] allBookFromDTO = listOfAllBooks.getBooks();

        List<String> listOfAllIsbn = new ArrayList<>();
        for (int i = 0; i < allBookFromDTO.length; i++) {
            listOfAllIsbn.add(allBookFromDTO[i].getIsbn());
        }

        if (userBook.length != allBookFromDTO.length) {

            for (int i = 0; i < userBook.length; i++) {
                listOfUserIsbn.add(userBook[i].getIsbn());
                if (listOfAllIsbn.contains(userBook[i].getIsbn())) {
                    listOfAllIsbn.remove(userBook[i].getIsbn());
                }
            }
        } else {
            logger.info("all books already add, so clean your cart ");
            apiHelperBook.deleteAllBooksByUser(userId, token);
        }

        apiHelperBook.addBookToUser(userId, token, listOfAllIsbn.get(0));
        isbnBook = listOfAllIsbn.get(0);
        listOfUserIsbn.add(listOfAllIsbn.get(0));


        ProfileDTO profileDTOAfterAdd = apiHelperBook.getProfileInfo(userId, token);
        BookDTO[] userBookAfterAdd = profileDTOAfterAdd.getBooks();

        Assert.assertEquals("Number of books ", beginningSizeOfUsersCart + 1, profileDTOAfterAdd.getBooks().length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < userBookAfterAdd.length; i++) {
            softAssertions.assertThat(userBookAfterAdd[i].getIsbn())
                    .as("Item number " + i).isEqualTo(listOfUserIsbn.get(i));

        }
        softAssertions.assertAll();
    }

    @After
    public void deleteAddingBook() {

        ProfileDTO profileDTOBeforeDelete = apiHelperBook.getProfileInfo(userId, token);
        Integer countOfBookBeforeDelete = profileDTOBeforeDelete.getBooks().length;

        apiHelperBook.deleteDetermineBook(isbnBook, userId, token);

        ProfileDTO profileDTOAfterDelete = apiHelperBook.getProfileInfo(userId, token);

        Assert.assertEquals("Count of book after delete ", countOfBookBeforeDelete - 1, profileDTOAfterDelete.getBooks().length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < profileDTOAfterDelete.getBooks().length; i++) {
            softAssertions.assertThat(profileDTOAfterDelete.getBooks()[i].getIsbn())
                    .as("Item number " + i).isNotEqualTo(isbnBook);

        }
        softAssertions.assertAll();
    }
}
