package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AuthorDTO {

//    public AuthorDTO(String username) {
//        this.username = username;
//    }

    String username;
    String avatar;

//    public AuthorDTO() {
//    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

//    @Override
//    public String toString() {
//        return "AuthorDTO{" +
//                "username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                '}';
//    }
}
