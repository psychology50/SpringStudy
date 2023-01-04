package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="user")
@Table(name="USER")
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToOne(mappedBy = "owner")
    @ToString.Exclude
    private Blog blog;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Like> likes = new ArrayList<Like>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

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

    public UserDomain() {
        
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}

