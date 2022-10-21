package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class QAToolsBooks {
    private final String USER_NAME = "krill";
    private final String PASSWORD = "*Takeiteasy8";
    public String token;
    public String uuid;

    Logger logger = Logger.getLogger(getClass());
    QAToolsApiHelper apiHelper = new QAToolsApiHelper();

    @Before
    public void loginAndClearListOfBooks() {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("userName", USER_NAME);
        bodyParams.put("password", PASSWORD);

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

        this.token = responseBody.getToken();
        this.uuid = responseBody.getUserId();
        logger.info("Token and UUID were received");

        apiHelper.deleteAllBooksInUserList(this.uuid, this.token);
    }

    @Test
    public void addBookToUserList() {
        QAToolsBooksListDTO booksList = apiHelper.getAllBooksInStore();
        String firstBookIsbn = booksList.getBooks()[0].getIsbn();

        apiHelper.addBooksToUserList(uuid, token, firstBookIsbn);
        QAToolsUsersBooks usersList = apiHelper.getUsersListOfBooks(uuid, token);

        Assert.assertTrue("New book does not displays in user's book-list", (usersList.getBooks().length == 1));
        Assert.assertEquals("Added book contains different isbn", usersList.getBooks()[0].getIsbn(), firstBookIsbn);
        logger.info(String.format("New book with isbn [%s]is displayed in user's book-list", firstBookIsbn));
    }
}
