package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDTO {
    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    String pages;
    String description;
    String website;

}
