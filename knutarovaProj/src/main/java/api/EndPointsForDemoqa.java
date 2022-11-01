package api;

public interface EndPointsForDemoqa {
    String baseUrl = "https://demoqa.com/";
    String LOGIN = baseUrl + "Account/v1/Login";
    String ALL_BOOKS_TO_STORE_AND_DELETE = baseUrl + "BookStore/v1/Books";
    String BOOKS_BY_USER = baseUrl + "Account/v1/User/{0}";
}
