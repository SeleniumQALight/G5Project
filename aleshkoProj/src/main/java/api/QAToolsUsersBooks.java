package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QAToolsUsersBooks {
    String userId;
    String username;
    QAToolsBooksDTO[] books;
}
