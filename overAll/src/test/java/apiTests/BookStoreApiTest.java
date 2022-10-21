package apiTests;


import java.util.ArrayList;
import java.util.List;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.book_store.ApiHelperBookShop;
import api.book_store.dto.Book;
import api.book_store.dto.BooksByUserDTO;
import api.book_store.dto.UserDTO;


public class BookStoreApiTest {
    String addedBookId;
    String  token;
    String userId ;
    Integer numberOfBooksByUser;

    ApiHelperBookShop apiHelperBookShop = new ApiHelperBookShop();

    @Before
    public void deleteAllBooksByUser(){
        UserDTO userDTO = apiHelperBookShop.loginByDefaultUser();
        token = userDTO.getToken();
        userId = userDTO.getUserId();

        apiHelperBookShop.deleteAllPostsForUser(token, userId);
    }


    @Test
    public void getBooks(){
        BooksByUserDTO booksByUserDTO = apiHelperBookShop.getBooksByUser(token, userId);

        List<String> booksAlreadyInList = new ArrayList<>();

        for (Book books: booksByUserDTO.getBooks()) {
            booksAlreadyInList.add(books.getIsbn());
        }

        System.out.println("Books in list" + booksAlreadyInList );
//        Assert.assertEquals(3, booksByUserDTO.getBooks().size());
        numberOfBooksByUser = booksByUserDTO.getBooks().size();
        Integer expectedNumberOfBooks =  numberOfBooksByUser + 1;

        List<Book> booksList = apiHelperBookShop.getAllBooksInStore().getBooks();

        System.out.println(booksList);
        System.out.println(booksList.size());
        List<String> booksInStore = new ArrayList<>();
        for (Book book : booksList ) {
            booksInStore.add(book.getIsbn());
        }
        System.out.println("Books in list" + booksAlreadyInList );
        System.out.println("Books in store " + booksInStore);
        booksInStore.removeAll(booksAlreadyInList);
        System.out.println("Unique books " + (booksInStore));
        Assert.assertNotEquals("All books is already in list", booksInStore.size(), 0);

        addedBookId = booksInStore.get(0);

        apiHelperBookShop.addBookToUserList(token, userId, addedBookId);


        booksByUserDTO = apiHelperBookShop.getBooksByUser(token, userId);
        Integer actualNumberOfBooks = booksByUserDTO.getBooks().size();
        Assert.assertEquals(expectedNumberOfBooks, actualNumberOfBooks);

    }




    @After
    public void deleteBook(){
        apiHelperBookShop.deleteBookFromUserList(token, addedBookId, userId);

        BooksByUserDTO booksByUserDTO =
                apiHelperBookShop.getBooksByUser(token, userId);

        Integer actualNumberOfBooks =  booksByUserDTO.getBooks().size();
        Assert.assertEquals(numberOfBooksByUser, actualNumberOfBooks);

    }



}
