package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get token for default user
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
}
