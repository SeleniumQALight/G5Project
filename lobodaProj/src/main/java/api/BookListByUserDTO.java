package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookListByUserDTO {
    String userId;
    String username;
    BookDTO[] books;

}
