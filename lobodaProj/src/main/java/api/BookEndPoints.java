package api;

public interface BookEndPoints {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String WORK_WITH_BOOKS = baseUrl + "/BookStore/v1/Books";
    String WORK_WITH_USER = baseUrl + "/Account/v1/User/{0}";


}
