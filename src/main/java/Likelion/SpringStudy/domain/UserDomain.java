package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity(name="user")
@Data
public class UserDomain {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role;

    public UserDomain() {}

    @Builder
    public UserDomain(Long id, String username, String nickname, String password,
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

}
