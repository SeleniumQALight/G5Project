package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder


public class BooksAddedDTOByDemoqa {
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("username")
    public String username;
    @JsonProperty("books")
    public List<BooksDTOByDemoqa> books;
}
