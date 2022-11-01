package api;

import lombok.*;


public class UserDemoqaDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public class UserBooksDTO {
        String isbn;
        String title;
        String subTitle;
        String author;
        String publish_date;
        String publisher;
        Integer pages;
        String description;
        String website;
    }

}
