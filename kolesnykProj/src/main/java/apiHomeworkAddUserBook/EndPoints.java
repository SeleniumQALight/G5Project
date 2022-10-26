package apiHomeworkAddUserBook;

public interface EndPoints {
    String baseUrl = "https://demoqa.com";
    String loginUrl = baseUrl + "/Account/v1/Login";
    String deleteUserBook = baseUrl + "/BookStore/v1/Books";
    String getUserBooks = baseUrl + "/Account/v1/User/{0}";
}
