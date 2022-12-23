package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name="user")
@Table(name="USER")
@Data
@Getter
public class UserDomain {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private Long id;

    @Column(name = "username") @NonNull
    private String username;
    @Column(name = "nickname", length = 15, unique = true) @NonNull
    private String nickname;
    @Column(name = "password") @NonNull
    private String password;
    @Column(name = "email") @NonNull
    private String email;
    @Column(name = "phone") @NonNull
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleType role;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;

    @OneToOne(mappedBy = "userDomain")
    private Blog blog;

    public UserDomain() {}

    @Builder
    public UserDomain(Long id, String username, String nickname, String password,
                      String email, String phone, RoleType role)
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

