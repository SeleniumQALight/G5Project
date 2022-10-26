package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QAToolsTokenDTO {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    Boolean isActive;
}
