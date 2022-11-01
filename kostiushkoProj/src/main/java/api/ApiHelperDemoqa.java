package api;


import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoqa {
    private String userToken;
    private String userId;
    Logger logger = Logger.getLogger(getClass());


    public UserDTO getAllBooksByUser(String token, String id) {
        return given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .log().all()
                .when()
                .get(EndPointsDemoqa.USER_BOOKS, id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(UserDTO.class);
    }
    public UserDTO getAllBooksByUser(){
        return getAllBooksByUser(userToken, userId);
    }
    public BooksStoreDTO getAllBooksFromStore() {
        logger.info("List of books in store: ");
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsDemoqa.BOOK_LIST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BooksStoreDTO.class);
    }

    public void login() {
        String USERNAME = "Filinoerti";
        String PASSWORD = "Filinoertii1991a!";
        login(USERNAME, PASSWORD);
    }

    public void login(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        LoginDTO responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when()
                .post(EndPointsDemoqa.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(LoginDTO.class);
        userToken = responseBody.getToken();
        userId = responseBody.getUserId();
        logger.info(String.format("User %s is logged in",userName));
    }

    public void deleteBooks() {
        given()
                .contentType(ContentType.JSON)
                .auth().oauth2(userToken)
                .queryParam("UserId", userId)
                .log().all()
                .when()
                .delete(EndPointsDemoqa.BOOK_LIST)
                .then()
                .statusCode(204)
                .log().all();

        Assert.assertEquals("Number of books is ", 0, getAllBooksByUser(userToken, userId).getBooks().length);
    }
    public void addBooksToUser(String token, String id, String isbn){
        HashMap<String, String> isbnList = new HashMap<>();
        isbnList.put("isbn", isbn);

        HashMap[] collectionOfIsbns = {isbnList};

        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userId", id);
        bodyParams.put("collectionOfIsbns", collectionOfIsbns);
        String responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .body(bodyParams.toMap())
                        .when()
                        .post(EndPointsDemoqa.BOOK_LIST)
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        Assert.assertTrue("Books was`t added", responseBody.contains(isbn));
        logger.info(String.format("Book with isbn = "+isbn+" was added to user library"));

    }
    public void addBooksToUser(String isbn){
        addBooksToUser(userToken, userId, isbn);
    }
}
