package api;

public interface EndPointsBook {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String DELETE_ALL_BOOKS = baseUrl + "/BookStore/v1/Books";
    String PROFILE_INFO = baseUrl + "/Account/v1/User/{0}";
    String LIST_OF_ALL_BOOKS = baseUrl + "/BookStore/v1/Books";

}
