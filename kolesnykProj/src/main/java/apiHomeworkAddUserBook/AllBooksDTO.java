package apiHomeworkAddUserBook;

import io.qameta.allure.Step;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AllBooksDTO {

    List<BookDTO> book;

}
