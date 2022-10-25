package api.book_store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    @JsonProperty("created_date")
    private String createdDate;
    private Boolean isActive;
}
