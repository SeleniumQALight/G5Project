package api.demoqaBookStoreHW2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelperBookStore {
    private final String USER_NAME = "Oleksandr";
    private final String PASSWORD = "P@ssword0000";
    public String token;
    public String uuid;
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Login by default user
     */
    public void login() {
        login(USER_NAME, PASSWORD);
    }

    public void login(String userName, String password) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userName", userName);
        bodyParams.put("password", password);

        UserDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(bodyParams.toMap())
                        .when()
                        .post(EndPointsBookStore.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(UserDTO.class);

        token = responseBody.getToken();
        uuid = responseBody.getUserId();
        logger.info("Token and UUID were received");
    }

    /**
     * Delete all books by default user
     */
    public void deleteAllBooksInUserList() {
        deleteAllBooksInUserList(uuid, token);
    }

    public void deleteAllBooksInUserList(String uuid, String token) {
        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .param("UserId", uuid)
                .when()
                .delete(EndPointsBookStore.GET_ALL_BOOKS)
                .then()
                .statusCode(204)
                .log().all();

        UserBooksDTO responseBody = getUsersListOfBooks(uuid, token);

        Assert.assertNotEquals("Number of books in list is not 0", 1, responseBody.books.length);
        logger.info("List of books was cleared");
    }

    /**
     * Delete book by user from profile
     *
     * @param isbn
     */
    public void deleteBookInUserListByIsbn(String isbn) {
        deleteBookInUserListByIsbn(uuid, token, isbn);
    }

    public void deleteBookInUserListByIsbn(String uuid, String token, String isbn) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("isbn", isbn);
        requestBody.put("userId", uuid);

        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .body(requestBody.toMap())
                .when()
                .delete(EndPointsBookStore.DELETE_BOOK_FROM_USER_LIST)
                .then()
                .statusCode(204)
                .log().all();
    }

    /**
     * Get all books list by user from store
     *
     * @return
     */
    public UserBooksDTO getUsersListOfBooks() {
        return getUsersListOfBooks(uuid, token);
    }

    public UserBooksDTO getUsersListOfBooks(String uuid, String token) {
        return given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .when()
                .get(EndPointsBookStore.GET_USER_BOOKS, uuid)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(UserBooksDTO.class);
    }

    /**
     * Get all books from store
     *
     * @return
     */
    public AllBooksDTO getAllBooksInStore() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBookStore.GET_ALL_BOOKS)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(AllBooksDTO.class);
    }

    /**
     * Add books to user profile
     *
     * @param isbn
     */
    public void addBooksToUserList(String isbn) {
        addBooksToUserList(uuid, token, isbn);
    }

    public void addBooksToUserList(String uuid, String token, String isbn) {
        HashMap<String, String> listOfIsbns = new HashMap<>();
        listOfIsbns.put("isbn", isbn);

        HashMap[] collectionOfIsbns = {listOfIsbns};

        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", uuid);
        requestBody.put("collectionOfIsbns", collectionOfIsbns);

        String responseBody =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                        .body(requestBody.toMap())
                        .when()
                        .post(EndPointsBookStore.GET_ALL_BOOKS)
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        Assert.assertTrue("Book was not added to list", responseBody.contains(isbn));
        logger.info(String.format("Book with isbn [%s] was added to user's list", isbn));
    }

    public ArrayList<String> isbnParser(BookDTO[] bookList) {
        ArrayList<String> listOfIsbn = new ArrayList<>();

        for (int i = 0; i < bookList.length; i++) {
            listOfIsbn.add(bookList[i].getIsbn());
        }
        return listOfIsbn;
    }
}