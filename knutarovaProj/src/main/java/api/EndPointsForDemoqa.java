package api;

public interface EndPointsForDemoqa {
    String baseUrl = "https://demoqa.com/";
    String LOGIN = baseUrl + "Account/v1/Login";
    String DELETE_BOOKS = baseUrl + "BookStore/v1/Books?UserId={userId}";
    String ALL_BOOKS_TO_STORE = baseUrl + "BookStore/v1/Books";
    String BOOKS_BY_USER = baseUrl + "Account/v1/User/{0}";
}
