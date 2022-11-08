package api.demoqaBookStoreHW2;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class UserBooksDTO {
    String userId;
    String username;
    BookDTO[] books;
}
