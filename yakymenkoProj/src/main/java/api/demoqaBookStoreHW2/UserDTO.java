package api.demoqaBookStoreHW2;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
