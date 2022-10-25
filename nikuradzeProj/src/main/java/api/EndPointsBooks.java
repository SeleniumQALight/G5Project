package api;

public interface EndPointsBooks {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String BOOKS_LIST = baseUrl + "/BookStore/v1/Books";
    String ADDED_BOOKS = baseUrl + "/Account/v1/User/{0}";
}
