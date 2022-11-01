package api;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    String userId;
    String username;
    UserBooksDTO[] books;
}

