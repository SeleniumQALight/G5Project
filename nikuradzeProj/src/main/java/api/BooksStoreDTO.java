package api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BooksStoreDTO {
    UserBooksDTO[] books;
}
