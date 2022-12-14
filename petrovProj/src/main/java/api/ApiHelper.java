package api;

import api.PrivatHW.EndPointsExchangeCours;
import api.PrivatHW.ExchangeCoursResponseDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.text.DecimalFormat;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public  final String USER_NAME = "petrov";
    private final String PASSWORD = "qwerty123456";

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

    private String getToken(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user_name);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
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
     * get posts for default user
     * @return
     */
    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String user_name) {
        return given()
                .spec(requestSpecification)
              .when()
                .get(EndPoints.POST_BY_USER, user_name)
              .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);
    }

    public void deletePostTillPresent() {
        deletePostTillPresent(USER_NAME, PASSWORD);
    }

    public void deletePostTillPresent(String user_name, String password) {
        PostDTO[] listOfPost = getAllPostsByUser(user_name);
        String token = getToken(user_name, password);
        for (int i = 0; i < listOfPost.length; i++) {

            deletePostById(token, listOfPost[i].getId());
            logger.info(
                    String.format(
                            "Post with id %s and title %s was deleted",
                            listOfPost[i].getId(), listOfPost[i].getTitle()));

        }
        Assert.assertEquals("Number of posts ", 0, getAllPostsByUser(user_name).length);
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
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();
        Assert.assertEquals("Message", "\"Success\"", response);

    }

    RequestSpecification requestSpecificationForRates = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build().queryParam("exchange").queryParam("json");


    public ExchangeCoursResponseDTO[] getExchangeRateByPrivat24(int coursIdQueryParam) {
        return given()
                .spec(requestSpecificationForRates)
                .queryParam("coursid", coursIdQueryParam)
                .when()
                .get(EndPointsExchangeCours.EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ExchangeCoursResponseDTO[].class);
    }


    public void createPost(String title, String userName, String password) {
        String token = getToken(userName.toLowerCase(), password);

        HashMap<String, String> requestParams = new HashMap<>();
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
                .statusCode(200);

    }

    public Double stringToDouble(String numberString){
        return  Double.parseDouble(numberString);
    }

    public String doubleToString(Double numberDouble){
        return  Double.toString(numberDouble);
    }


    public String decimalFormatCurrency(String numberString){
        DecimalFormat df = new DecimalFormat("####0.00");
        double e = stringToDouble(numberString);
        df.format(e);
        return  doubleToString(e);
    }
}
