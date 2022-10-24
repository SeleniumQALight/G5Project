package apiHomeworkAddUserBook;

import lombok.*;
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserBooksDTO {

    String userId;
    String username;
    List<BookDTO> books;
}
