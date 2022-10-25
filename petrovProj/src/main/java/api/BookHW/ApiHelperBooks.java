package api.BookHW;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    public final String USER_NAME_DEFAULT = "StanLeeReader";
    public final String USER_PASS_DEFAULT = "Qwerty123@";

    Logger logger = Logger.getLogger(getClass());

    UserDTO userDTO = login();
    String token = userDTO.getToken();
    String userId = userDTO.getUserId();

    public UserDTO login(){
        return login(USER_NAME_DEFAULT, USER_PASS_DEFAULT);
    }


    public UserDTO login(String userName, String userPass){
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", userPass);
        requestParams.put("userName", userName);

        UserDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParams.toMap())
                .when()
                        .post(EndPointBook.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(UserDTO.class);

        return responseBody;
    }


    public UserBooksDTO getAllBooksByUser() {
        return getAllBooksByUser(userId, token);
    }

    private UserBooksDTO getAllBooksByUser(String userId, String token) {
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointBook.GET_USER_BOOKS, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(UserBooksDTO.class);
    }



//    public void deleteBooksTillPresent(){
//        deleteBooksTillPresent(userId, token);
//    }
//
//    public void deleteBooksTillPresent(String user_id, String token){
//
//        UserBooksDTO resultWithListOfBooks = getAllBooksByUser(user_id, token);
//        BooksDTO[] listOfBooks = resultWithListOfBooks.getBooks();
//
//        for (int i = 0; i < listOfBooks.length; i++) {
//            logger.info("listOfBooks -  "+listOfBooks[i]);
//            deleteBooksByUserId(token, listOfBooks[i].getIsbn());
//            logger.info(
//                    String.format(
//                            "Books with User_id %s was deleted",
//                            listOfBooks[i].getIsbn()));
//
//        }
//        Assert
//                .assertEquals("Number of posts ", 0,
//                        getAllBooksByUser(user_id, token).getBooks().length);
//
//    }

    public void deleteBooksByUserId(){
        deleteBooksByUserId(userId, token);
    }
    public void deleteBooksByUserId( String userId, String token) {

        logger.info("-------------");
        logger.info("token = "+token);
        logger.info("userId = "+userId);
        logger.info("-------------");

        String response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndPointBook.DELETE_USER_BOOKS, userId)
                .then()
                .statusCode(204)
                .log().all()
                .extract().response().getBody().asString();

    }


    public AllBooksDTO getAllBooksFromSite(){
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointBook.BOOKS)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(AllBooksDTO.class);
    }

}
