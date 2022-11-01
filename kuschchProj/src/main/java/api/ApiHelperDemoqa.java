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

public class ApiHelperDemoqa {
    public static final String USER_NAME = "yuriy";
    private final String PASS = "Yuriy@123";
    private String token;
    private String id;

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Login
     * @return
     */
    public LoginDemoqaDTO login() {
        return login(USER_NAME, PASS);
    }

    public LoginDemoqaDTO login(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", user_name);
        requestParams.put("password", password);

        LoginDemoqaDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(EndPointsDemoqa.LOGIN_DEMOQA)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(LoginDemoqaDTO.class);
        token = responseBody.getToken();
        id = responseBody.getUserId();
        logger.info(String.format("User %s is logged",user_name));
        return (LoginDemoqaDTO) responseBody;
    }

    /**
     * Delete oll books
     * @return
     */
    public void deleteUsersBooks() {

        deleteUsersBooks(token, id);
    }
    private void deleteUsersBooks(String token, String id) {

                given().header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .queryParam("UserId", id)
                        .log().all()
                .when()
                        .delete(EndPointsDemoqa. BOOKS_DEMOQA)
                .then()
                        .statusCode(204)
                        .log().all();

        logger.info("All books have been deleted");
    }

    /**
     * Get a list of users books
     * @return
     */
    public UserDemoqaDTO getListBooks() {
        logger.info("List of books: ");
        return given()
                .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(EndPointsDemoqa.ADD_BOOKS)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(UserDemoqaDTO.class);

    }

    /**
     * Get books in store
     * @return
     */
    public AllBooksStoryDemoqaDTO getAllBooksStoreDemoqa() {
        logger.info("List of all books: ");
            return given()
                        .spec(requestSpecification)
                .when()
                        .get(EndPointsDemoqa.BOOKS_DEMOQA)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(AllBooksStoryDemoqaDTO.class);
    }


    /**
     * Add new book
     * @return
     */
    private void addBooksInProfile(String token, String id, String isbn){
        HashMap<String, String> isbnList = new HashMap<>();
        isbnList.put("isbn", isbn);
        HashMap[] isbns = {isbnList};
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userId", id);
        bodyParams.put("collectionOfIsbns", isbns);

        String responseBody =
                given()
                        .header("Authorization", "Bearer " + token)
                        .spec(requestSpecification)
                        .body(bodyParams.toMap())
                .when()
                        .post(EndPointsDemoqa.BOOKS_DEMOQA)
                .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        Assert.assertTrue("Book was not added", responseBody.contains(isbn));
        logger.info(String.format("Book with isbn = %s was added", isbns));    }

    public void addBooksInProfile(String isbn) {
        addBooksInProfile(token, id, isbn);
    }
}
