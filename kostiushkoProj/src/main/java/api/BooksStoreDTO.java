package api;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BooksStoreDTO {
    UserBooksDTO[] books;
}
