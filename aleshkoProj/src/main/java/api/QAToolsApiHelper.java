package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class QAToolsApiHelper {
    private final String USER_NAME = "krill";
    private final String PASSWORD = "*Takeiteasy8";
    public String token;
    public String uuid;

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public void login() {
        login(USER_NAME, PASSWORD);
    }

    public void login(String userName, String password) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userName", userName);
        bodyParams.put("password", password);

        QAToolsTokenDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(bodyParams.toMap())
                        .when()
                        .post(BooksEndPoints.login)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(QAToolsTokenDTO.class);

        token = responseBody.getToken();
        uuid = responseBody.getUserId();
        logger.info("Token and UUID were received");
    }

    public void deleteAllBooksInUserList() {
        deleteAllBooksInUserList(uuid, token);
    }

    public void deleteAllBooksInUserList(String uuid, String token) {
        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .param("UserId", uuid)
                .when()
                .delete(BooksEndPoints.booksUrl)
                .then()
                .statusCode(204)
                .log().all();

        QAToolsUsersBooks responseBody = getUsersListOfBooks(uuid, token);

        Assert.assertNotEquals("Number of books in list is not 0", 1, responseBody.books.length);
        logger.info("List of books was cleared");
    }

    public QAToolsUsersBooks getUsersListOfBooks() {
        return getUsersListOfBooks(uuid, token);
    }

    public QAToolsUsersBooks getUsersListOfBooks(String uuid, String token) {
        return given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .when()
                .get(BooksEndPoints.getAllBooksByUser, uuid)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(QAToolsUsersBooks.class);
    }

    public QAToolsBooksListDTO getAllBooksInStore() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(BooksEndPoints.booksUrl)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(QAToolsBooksListDTO.class);
    }

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
                        .post(BooksEndPoints.booksUrl)
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        Assert.assertTrue("Book was not added to list", responseBody.contains(isbn));
        logger.info(String.format("Book with isbn [%s] was added to user's list", isbn));
    }
}
