package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookDTO {
    String isbn;
    String title;
    String subTitle;
    String author;

    @JsonProperty("publish_date")
    String publishDate;

    String publisher;
    Integer pages;
    String  description;
    String website;

}
