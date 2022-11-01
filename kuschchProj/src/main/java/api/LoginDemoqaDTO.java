package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginDemoqaDTO {
        private String userId;
        private String username;
        private String password;
        private String expires;
        private String created_date;
        private Boolean isActive;
        private String token;
}
