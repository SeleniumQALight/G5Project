package api;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    private String token;
    private String id;
    Logger logger = Logger.getLogger(getClass());


    public UserDTO getAllBooksByUser(String token, String id) {
        logger.info("List of books added to user's library: ");
        return given()
                    .contentType(ContentType.JSON)
                    .auth().oauth2(token)
                    .log().all()
               .when()
                    .get(EndPointsBooks.ADDED_BOOKS, id)
               .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody().as(UserDTO.class);
    }
    public UserDTO getAllBooksByUser(){
        return getAllBooksByUser(token, id);
    }
    public BooksStoreDTO getAllBooksFromStore() {
        logger.info("List of books in store: ");
        return given()
                    .contentType(ContentType.JSON)
                    .log().all()
               .when()
                    .get(EndPointsBooks.BOOKS_LIST)
               .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody().as(BooksStoreDTO.class);
    }

    public void login() {
        String USERNAME = "snik";
        String PASSWORD = "P@ssw0rd";
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
                        .post(EndPointsBooks.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginDTO.class);
        token = responseBody.getToken();
        id = responseBody.getUserId();
        logger.info(String.format("User %s is logged in",userName));
    }

    public void deleteBooks() {
                given()
                        .contentType(ContentType.JSON)
                        .auth().oauth2(token)
                        .queryParam("UserId", id)
                        .log().all()
                .when()
                        .delete(EndPointsBooks.BOOKS_LIST)
                .then()
                        .statusCode(204)
                        .log().all();

        logger.info("All books were removed");
        Assert.assertEquals("Books number is ", 0, getAllBooksByUser(token, id).getBooks().length);
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
                            .post(EndPointsBooks.BOOKS_LIST)
                    .then()
                            .log().all()
                            .statusCode(201)
                            .extract().response().getBody().asString();

            Assert.assertTrue("Book was not added", responseBody.contains(isbn));
            logger.info(String.format("Book with isbn = %s was added to user library", isbn));

    }
    public void addBooksToUser(String isbn){
        addBooksToUser(token, id, isbn);
    }
}