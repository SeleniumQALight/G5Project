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
        deleteUsersBooks(id, token);
    }
    private void deleteUsersBooks(String id, String token) {

                given().header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .queryParam("UserId", id)
                        .log().all()
                .when()
                        .delete(EndPointsDemoqa. DELETE_BOOKS)
                .then()
                        .statusCode(204)
                        .log().all()
                        .extract().response().getBody().asString();

        getListBooks(token, id);

        logger.info("All books have been deleted");
        Assert.assertEquals("number of books ", 0, getListBooks(token, id));
    }

    /**
     * Get a list of users books
     * @return
     */
    public int getListBooks(String token, String id) {
        String actualResponce =
                given().header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(EndPointsDemoqa.LIST_BOOKS, id)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        JSONObject listBooks = new JSONObject(actualResponce);
        int bookKount = listBooks.getJSONArray("books").length();
        logger.info("number of books " + bookKount);
        return bookKount;
    }

    /**
     * Get default Id book
     * @return
     */
    public String getIsbnSomeBookDemoqa() {
        String actualResponce =
                given()
                        .spec(requestSpecification)
                .when()
                        .get(EndPointsDemoqa.LIST_BOOKS_DEMOQA)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        JSONObject userJson = new JSONObject(actualResponce);
        String someBook = userJson.getJSONArray("books").getJSONObject(0).getString("isbn");

        logger.info("book isbn added" + someBook);
        return someBook;
    }

    /**
     * Add new book
     * @return
     */
    private void addBooksInProfile(String token, String id){
        String someBook = getIsbnSomeBookDemoqa();

        logger.info("book isbn added" + someBook);
        HashMap<String, String> isbnList = new HashMap<>();
        isbnList.put("isbn", someBook);
        HashMap[] collectionOfIsbns = {isbnList};

        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userId", id);
        bodyParams.put("collectionOfIsbns", collectionOfIsbns);

        String responseBody =
                given()
                        .header("Authorization", "Bearer " + token)
                        .spec(requestSpecification)
                        .body(bodyParams.toMap())
                .when()
                        .post(EndPointsDemoqa.ADDBOOK)
                .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        Assert.assertTrue("Book was not added", responseBody.contains(someBook));
        logger.info(String.format("Book with isbn = %s was added", someBook));
        Assert.assertEquals("number of books ", 1, getListBooks(token, id));

    }

    public void addBooksInProfile() {
        addBooksInProfile(token, id);
    }
}
