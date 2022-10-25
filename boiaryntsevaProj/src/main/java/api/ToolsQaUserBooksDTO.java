package api;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ToolsQaUserBooksDTO {
    String userId;
    String username;
    List<ToolsQABookDetailsDTO> books;
}
