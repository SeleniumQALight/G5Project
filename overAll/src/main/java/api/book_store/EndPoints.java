package api.book_store;


public class EndPoints {
    private EndPoints() {
    }

    public static final String BASE_URL = "https://demoqa.com";

    public static final String LOGIN =  "/Account/v1/Login";
    public static final String BOOKS_BY_USER =  "/Account/v1/User/{0}";

    public static final String ALL_BOOKS_FOR_USER =  "/BookStore/v1/Books";
    public static final String BOOK_FOR_USER =  "/BookStore/v1/Book";


}
