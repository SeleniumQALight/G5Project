package api.BookHW;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UserDTO {
    String userId;
    String token;
    String username;
    String password;
    String expires;
    String created_date;
    Boolean isActive;
}
