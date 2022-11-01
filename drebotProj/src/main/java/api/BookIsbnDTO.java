package api;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookIsbnDTO {
    AddBookDTO books[];
}
