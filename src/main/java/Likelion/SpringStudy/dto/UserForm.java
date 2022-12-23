package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.RoleType;
import Likelion.SpringStudy.domain.UserDomain;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class UserForm {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    private String role;

    @Builder
    public UserForm(Long id, String username, String nickname, String password,
                    String email, String phone, String role)
    {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public UserDomain toEntity() {
        return UserDomain.builder()
                .id(id)
                .username(username)
                .nickname(nickname)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .phone(phone)
                .role(RoleType.USER)
                .build();
    }
}
