package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public static final String USER_NAME = "rosko48";
    private final String PASSWORD = "12345678912345";

    Logger logger = Logger.getLogger(getClass());


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * get token for default user
     * @return
     */
    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user_name);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .body(requestParams.toMap())
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"","");

    }

    /**
     * get post for DEFAULT user
     * @return
     */
    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String user_name){
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, user_name)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);
    }

    public void deletePostsTillPresent() {
        deletePostsTillPresent(USER_NAME, PASSWORD);
    }

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(userName, password);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id : %s and title : %s was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of posts : ", 0 , getAllPostsByUser(userName).length);
    }

    private void deletePostById(String token, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", token);

        String response = given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().body().asString();
        Assert.assertEquals("Message : ", "\"Success\"", response);
    }

    public void createPost(String title, String userName, String password) {
        String token = getToken(userName.toLowerCase(),password);

        HashMap<String , String > requestParams = new HashMap<>();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "no");
        requestParams.put("token", token);

        given()
                .spec(requestSpecification)
                .body(requestParams)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
