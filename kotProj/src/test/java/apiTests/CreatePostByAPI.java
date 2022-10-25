package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class CreatePostByAPI {
    ApiHelper apiHelper= new ApiHelper();

@Before //deleting all posts
public void deleteAllPosts (){
    apiHelper.deletePostsBeforeCurrent();
}

    @Test
    public void CreatePostByAPI(){
     String token = apiHelper.getToken();

        HashMap<String, String> requestParams=new HashMap<>();
        requestParams.put("title", "New post from Api");
        requestParams.put("body","post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "yes");
        requestParams.put("token", token);
String response =
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestParams)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();
        Assert.assertEquals("Message","\"Congrats.\"",response);
//робимо перевірку скільки в юзерів постів і чи було створено саме той, що зверху |^
        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser();  //default user as for token
        Assert.assertEquals("Number of posts", 1, actualPostDTO.length);


 //перевірка, що це саме наш пост по назві
  PostDTO[] expectedPostDTO = {
          PostDTO.builder()
                  .title(requestParams.get("title"))
                  .body(requestParams.get("body"))
                  .select1(requestParams.get("select1"))
                  .uniquePost(requestParams.get("uniquePost"))
                  .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
                  .isVisitorOwner(false)
                  .build()
            };
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostDTO[0])
                .isEqualToIgnoringGivenFields(expectedPostDTO[0],"id", "createdDate","author");
        softAssertions.assertThat(actualPostDTO[0].getAuthor()).
                isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(),"avatar");
        softAssertions.assertAll();
    }
}
