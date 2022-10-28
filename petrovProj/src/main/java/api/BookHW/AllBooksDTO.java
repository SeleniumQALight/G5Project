package api.BookHW;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AllBooksDTO {
    BooksDTO[] books;
}
