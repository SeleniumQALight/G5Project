package api;

public interface EndPointsDemoqa {
        String baseUrlDemoqa = "https://demoqa.com";
        String LOGIN_DEMOQA = baseUrlDemoqa + "/Account/v1/Login";
        String LIST_BOOKS_DEMOQA = baseUrlDemoqa + "/BookStore/v1/Books";
        String DELETE_BOOKS = baseUrlDemoqa + "/BookStore/v1/Books";
        String LIST_BOOKS = baseUrlDemoqa + "/Account/v1/User/{0}";
        String ADDBOOK = baseUrlDemoqa + "/BookStore/v1/Books";
}


