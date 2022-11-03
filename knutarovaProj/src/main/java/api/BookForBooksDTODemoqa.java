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

public class BookForBooksDTODemoqa {
    @JsonProperty("books")
    public List<BooksDTOByDemoqa> books;
}
