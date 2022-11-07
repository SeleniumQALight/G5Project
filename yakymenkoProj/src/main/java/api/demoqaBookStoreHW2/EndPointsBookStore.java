package api.demoqaBookStoreHW2;

public interface EndPointsBookStore {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String GET_ALL_BOOKS = baseUrl + "/BookStore/v1/Books";
    String DELETE_BOOK_FROM_USER_LIST = baseUrl + "/BookStore/v1/Book";
    String GET_USER_BOOKS = baseUrl + "/Account/v1/User/{0}";
}
