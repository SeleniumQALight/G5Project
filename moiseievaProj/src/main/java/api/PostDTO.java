package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostDTO {
    @JsonProperty("_id")
    String id;

    String title;
    String body;
    String select1;
    String uniquePost;
    String createdDate;
    AuthorDTO author;
    Boolean isVisitorOwner;

}
