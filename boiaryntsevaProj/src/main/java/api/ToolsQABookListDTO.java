package api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ToolsQABookListDTO {
    List<ToolsQABookDetailsDTO> books;
}
