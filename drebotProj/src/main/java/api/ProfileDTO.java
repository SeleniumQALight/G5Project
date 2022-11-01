package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProfileDTO {
    String userId;

    @JsonProperty("username")
    String userName;

    BookDTO books[];
}
