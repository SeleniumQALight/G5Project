package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UserDTODemoqa {
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
    @JsonProperty("token")
    public String token;
    @JsonProperty("expires")
    public String expires;
    @JsonProperty("created_date")
    public String createdDate;
    @JsonProperty("isActive")
    public Boolean isActive;

}
