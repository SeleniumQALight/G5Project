package api;

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
