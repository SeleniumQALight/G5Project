package apiHomeworkAddUserBook;

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
    int pages;
    String description;
    String website;
}
