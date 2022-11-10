package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UserBooksDemoqaDTO {
        String userId;
        String username;
        UserDemoqaDTO[] books;

}
