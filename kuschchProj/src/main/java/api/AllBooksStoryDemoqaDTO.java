package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AllBooksStoryDemoqaDTO {
    private UserDemoqaDTO[] books;
}
