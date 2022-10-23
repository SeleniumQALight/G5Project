package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookLoginDTO {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    Boolean isActive;

}
