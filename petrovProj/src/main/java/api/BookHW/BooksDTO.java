package api.BookHW;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class BooksDTO {
    public String isbn;
    public String title;
    public String subTitle;
    public String author;
    public String publish_date;
    public String publisher;
    public Integer pages;
    public String description;
    public String website;
}
