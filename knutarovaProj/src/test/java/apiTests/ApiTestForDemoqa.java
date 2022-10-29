package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestForDemoqa {
    String token;
    String userId;
    ApiHelperForDemoqa apiHelperForDemoqa = new ApiHelperForDemoqa();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllBooks() {
        ApiHelperForDemoqa apiHelperForDemoqa = new ApiHelperForDemoqa();
        UserDTODemoqa userDTO = apiHelperForDemoqa.loginByUser();
        token = userDTO.getToken();
        userId = userDTO.getUserId();
        apiHelperForDemoqa.deleteAllBooksTillPresent();
    }

    @Test
    public void apiTestAddBooksInDemoqa() {

        BookForBooksDTODemoqa allBooksDTO = apiHelperForDemoqa.getAllBooksInStore();
        String isbnFromFirstBookFromList = String.valueOf(allBooksDTO.books.get(0).getIsbn());
        System.out.println(isbnFromFirstBookFromList);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("isbn", isbnFromFirstBookFromList);
        List<HashMap<String, String>> listOdIsbn = new ArrayList<>();
        listOdIsbn.add(hashMap);
        JSONObject requestParams = new JSONObject();
        requestParams.put("collectionOfIsbns", listOdIsbn);
        requestParams.put("userId", userId);

            given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .body(requestParams.toMap())
                .log().all()
            .when()
                .post(EndPointsForDemoqa.ALL_BOOKS_TO_STORE)
            .then()
                .statusCode(201)
                .log().all();

        BooksAddedDTOByDemoqa responseCountOfBooksByUser = apiHelperForDemoqa.checkCountOfBooksByUser(userId, token);
        SoftAssertions softAssertions = new SoftAssertions();
        Assert.assertEquals("Count of books = 1", 1, responseCountOfBooksByUser.books.size());
        String isbnFromAddedBookByUser = String.valueOf(responseCountOfBooksByUser.books.get(0).getIsbn());
        Assert.assertEquals("ISBN by added book is equal to first book from list", isbnFromFirstBookFromList
                , isbnFromAddedBookByUser);
                softAssertions.assertAll();
        logger.info(String.format("Count of books = 1", 1, responseCountOfBooksByUser.books.size()));
        logger.info(String.format("ISBN by added book is equal to first book from list", isbnFromFirstBookFromList
                , isbnFromAddedBookByUser));
    }

}
