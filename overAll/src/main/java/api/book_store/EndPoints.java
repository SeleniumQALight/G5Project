package api.book_store;


public class EndPoints {
    private EndPoints() {
    }

    private static final String BASE_URL = "https://demoqa.com";

    public static final String LOGIN = BASE_URL + "/Account/v1/Login";
    public static final String BOOKS_BY_USER = BASE_URL + "/Account/v1/User/{0}";

    public static final String ALL_BOOKS_FOR_USER = BASE_URL + "/BookStore/v1/Books";
    public static final String BOOK_FOR_USER = BASE_URL + "/BookStore/v1/Book";


}
