package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QAToolsUsersBooksDTO {
    String userId;
    String username;
    QAToolsBooksDTO[] books;
}
