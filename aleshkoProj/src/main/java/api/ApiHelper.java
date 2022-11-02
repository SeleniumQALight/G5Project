package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get token for default user
     *
     * @return
     */
    public String getToken() {
        return getToken(TestData.USER_NAME, TestData.PASSWORD);
    }

    public String getToken(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user_name);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
//                .contentType(ContentType.JSON)
//                .log().all()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)

                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    /**
     * Get all posts for default user
     *
     * @return
     */
    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(TestData.USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);
    }

    /**
     * Delete all posts for default user
     */
    public void deletePostsTillPresent() {
        deletePostsTillPresent(TestData.USER_NAME, TestData.PASSWORD);
    }

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(userName, password);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(listOfPosts[i].getId(), token);
            logger.info(String.format("Post with id %s and title %s was deleted",
                    listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of posts ", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String id, String token) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", token);

        String response = given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Message ", "\"Success\"", response);
    }

    public void createPost(String title, String userName, String password) {
        String token = getToken(userName.toLowerCase(), password);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "yes");
        requestParams.put("token", token);

        given()
                .spec(requestSpecification)
                .body(requestParams)
        .when()
                .post(EndPoints.CREATE_POST)
        .then()
                .statusCode(200);
    }
}
