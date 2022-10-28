package api.BookHW;


public interface EndPointBook {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String BOOKS = baseUrl + "/BookStore/v1/Books";
    String GET_USER_BOOKS = baseUrl + "/Account/v1/User/{0}";

}
