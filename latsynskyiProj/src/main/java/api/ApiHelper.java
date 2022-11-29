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
public static String USER_NAME = "ooo";
    private final String PASSWORD = "123456789012";

    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get token for default user
     * @return
     */
    public String getToken() {


    return getToken(USER_NAME,PASSWORD);
    }

    public String getToken(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username",user_name);
        requestParams.put("password",password);

        ResponseBody responseBody =
                given()

                       // .contentType(ContentType.JSON)
                        // .log().all()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"","");
    }

    /**
     * Get post for DEFAULT user
     * @return
     */
    public PostDTO[] getAllPostsByUser() {
    return getAllPostsByUser(USER_NAME);



    }

    private PostDTO[] getAllPostsByUser(String user_name) {
    return given()
            .spec(requestSpecification)
            .when()
            .get(EndPoints.POST_BY_USER,user_name)
            .then()
            .statusCode(200)
            .log().all()
            .extract().response().getBody().as(PostDTO[].class);



    }

    public void deletePostTillPresent() {
    deletePostTillPresent(USER_NAME,PASSWORD);
    }

    public void deletePostTillPresent(String userName, String password) {
    PostDTO[] listOfPosts = getAllPostsByUser(userName);
    String token = getToken(userName,password);

    for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s was deleted",listOfPosts[i].getId()
            ,listOfPosts[i].getTitle()));

        }
        Assert.assertEquals("Number of posts",0,getAllPostsByUser(userName).length);

    }

    private void deletePostById(String token, String id) {
    JSONObject bodyParams = new JSONObject();
    bodyParams.put("token",token);

   String response =  given()
            .spec(requestSpecification)
            .body(bodyParams.toMap())
            .when()
            .delete(EndPoints.DELETE_POST,id)
            .then()
            .statusCode(200)
            .log().all()
           .extract().response().getBody().asString();

   Assert.assertEquals("Message ","\"Success\"",response);

    }

    public void createPost(String title, String userName, String password) {
    String token = getToken(userName.toLowerCase(),password);

        HashMap<String,String> requestParams = new HashMap<>();
        requestParams.put("title",title);
        requestParams.put("body","post body");
        requestParams.put("select1","One Person");
        requestParams.put("uniquePost","no");
        requestParams.put("token",token);
        given()
                .spec(requestSpecification)
                .body(requestParams)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200);

    }
    String ccy_EUR_RUR_USD = "11";
    public  void getCurrencyPBByAPI(String currency){

        Logger logger = Logger.getLogger(getClass());

        GetDTOPB[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPB.GET_FOR_CURRENCY,ccy_EUR_RUR_USD)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(GetDTOPB[].class);
        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)){
                TestData.CURRENCY_NAME_FROM_API =responseBody[i].getCcy();
                TestData.CURRENCY_BUY_FROM_API =responseBody[i].getBuy();
                TestData.CURRENCY_SALE_FROM_API =responseBody[i].getSale(); }
        }
        logger.info(TestData.CURRENCY_NAME_FROM_API +" BUY= "+TestData.CURRENCY_BUY_FROM_API +" SALE= "+TestData.CURRENCY_SALE_FROM_API);
       }
        }





