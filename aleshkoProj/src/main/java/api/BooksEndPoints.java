package api;

public interface BooksEndPoints {
    String baseUrl = "https://demoqa.com";

    String login = baseUrl + "/Account/v1/Login";
    String getAllBooksByUser = baseUrl + "/Account/v1/User/{0}";
    String booksUrl = baseUrl + "/BookStore/v1/Books";
    String deleteBookFromUserList = baseUrl + "/BookStore/v1/Book";
}
