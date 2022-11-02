package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfoDTO {
    public String userId;

    @JsonProperty("username")
    public String userName;

    public String password;
    public String token;
    public String expires;

    @JsonProperty("created_date")
    public String createdDate;

    public Boolean isActive;

}

