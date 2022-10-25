package apiHomeworkAddUserBook;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AllBooksDTO {

    List<BookDTO> books;

}
