package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class BooksDTOByDemoqa {
    @JsonProperty("isbn")
    public String isbn;
    @JsonProperty("title")
    public String title;
    @JsonProperty("subTitle")
    public String subTitle;
    @JsonProperty("author")
    public String author;
    @JsonProperty("publish_date")
    public String publishDate;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("pages")
    public Integer pages;
    @JsonProperty("description")
    public String description;
    @JsonProperty("website")
    public String website;
}
