package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QAToolsBooksListDTO {
    QAToolsBooksDTO[] books;
}
