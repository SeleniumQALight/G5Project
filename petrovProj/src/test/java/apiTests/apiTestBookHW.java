package apiTests;

import api.BookHW.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class apiTestBookHW {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    UserDTO userDTO;
    String token;
    String userId;


    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllUsersBooks(){
        userDTO = apiHelperBooks.login();
        token = userDTO.getToken();
        userId = userDTO.getUserId();
        apiHelperBooks.deleteBooksByUserId(userId);
    }


    @Test
    public void AddBooks(){

        String isnbBook = apiHelperBooks.getAllBooksFromSite().getBooks()[0].getIsbn();
        JSONObject requestParams = new JSONObject();
        ArrayList<Map<String, String>> collectionOfIsbn = new ArrayList<>();
        Map<String, String> isbn = new HashMap<>();
        isbn.put("isbn", isnbBook);
        collectionOfIsbn.add(isbn);
        requestParams.put("collectionOfIsbns", collectionOfIsbn);
        requestParams.put("userId", userId);


        String response = given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when().post(EndPointBook.BOOKS)
                .then()
                .statusCode(201)
                .log().all()
                .extract().response().getBody().asString();

        logger.info("response add books "+response);


        UserBooksDTO usersBooks = apiHelperBooks.getBookByUser(userId);

        Assert.assertEquals("Number of books ", 1, usersBooks.getBooks().length);
        Assert.assertEquals("id of books ", isnbBook, usersBooks.getBooks()[0].getIsbn());



}}
