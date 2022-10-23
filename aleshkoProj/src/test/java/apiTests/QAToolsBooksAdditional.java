package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class QAToolsBooksAdditional {

    private final String USER_NAME = "krill";
    private final String PASSWORD = "*Takeiteasy8";
    public String token;
    public String uuid;
    public String addedBookIsbn = "";

    Logger logger = Logger.getLogger(getClass());
    QAToolsApiHelper apiHelper = new QAToolsApiHelper();

    @Before
    public void userLogin() {
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
    }

    @Test
    public void addNewBookToUsersList() {
        QAToolsBooksDTO[] userBookList = apiHelper.getUsersListOfBooks(uuid, token).getBooks();
        QAToolsBooksDTO[] storeBookList = apiHelper.getAllBooksInStore().getBooks();

        ArrayList<String> usersIsbnArray = apiHelper.isbnParser(userBookList);
        ArrayList<String> storeBooksIsbnArray = apiHelper.isbnParser(storeBookList);

        for (int i = 0; i < storeBooksIsbnArray.size(); i++) {
            if (!usersIsbnArray.contains(storeBooksIsbnArray.get(i))) {
                addedBookIsbn = storeBooksIsbnArray.get(i);
                apiHelper.addBooksToUserList(uuid, token, addedBookIsbn);
                break;
            }
        }
        if (addedBookIsbn.equals("")) {
            logger.info("All books in store already added to user collection");
        }
    }

    @After
    public void deleteAddedBookFromUsersList() {
        if (!addedBookIsbn.equals("")) {
            apiHelper.deleteBookInUserListByIsbn(uuid, token, addedBookIsbn);
        }
    }
}
