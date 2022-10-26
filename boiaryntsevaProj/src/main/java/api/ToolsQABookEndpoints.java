package api;

public interface ToolsQABookEndpoints {

    String BASE_URL="https://demoqa.com";
    String LOGIN=BASE_URL+"/Account/v1/Login";
    String USER_BOOKS=BASE_URL+"/Account/v1/User/{UserId}";
    String BOOKS_LIST = BASE_URL+"/BookStore/v1/Books";

}
