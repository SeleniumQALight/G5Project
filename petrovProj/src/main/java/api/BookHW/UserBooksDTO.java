package api.BookHW;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UserBooksDTO {
String userId;
String username;
BooksDTO[] books;

}
