package api.demoqaBookStoreHW2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AllBooksDTO {
    BookDTO[] books;
}
