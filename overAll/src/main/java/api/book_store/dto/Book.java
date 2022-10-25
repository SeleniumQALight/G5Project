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
public class Book {

    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    @JsonProperty("publish_date")
    private String publishDate;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;

}