package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthorDTO {
    String username;
    String avatar;

//    public AuthorDTO() { //@NoArgsConstructor
//    }

//    public AuthorDTO(String username) { //@AllArgsConstructor
//        this.username = username;
//    }

//    public String getUsername() { //@Getter
//        return username;
//    }
//
//    public void setUsername(String username) { //@Setter
//        this.username = username;
//    }
//
//    public String getAvatar() { //@Getter
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) { //@Setter
//        this.avatar = avatar;
//    }

//    @Override
//    public String toString() { //@ToString
//        return "AuthorDTO{" +
//                "username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                '}';
//    }
}