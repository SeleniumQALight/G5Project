package apiTests;

import api.BookApiHelper;
import org.json.JSONObject;
import org.junit.Test;

public class ApiBookStoreTest {
    BookApiHelper bookApiHelper = new BookApiHelper();

    @Test
    public void addBookToProfile(){
        JSONObject userIdAndToken = bookApiHelper.getUserIdAndToken();
        String userId = userIdAndToken.get("userId").toString();
        String token = userIdAndToken.get("token").toString();

        bookApiHelper.deleteAllBooksByUserId(token,userId);
        String isbn = bookApiHelper.getIsbnForFirstBook();
        bookApiHelper.addBookToUser(token,userId,isbn);







    }


}
