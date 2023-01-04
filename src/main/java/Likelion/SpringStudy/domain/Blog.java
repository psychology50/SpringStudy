package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="blog")
@Table(name="BLOG")
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BLOG_ID")
    private Long id;

    @Column(name="blog_name")
    @NotNull
    private String blog_name;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private UserDomain owner;

    @OneToMany(mappedBy = "blog")
    private List<Post> posts = new ArrayList<Post>();

    @Builder
    public Blog(Long id, String blog_name) {
        this.id = id;
        this.blog_name = blog_name;
    }

    public Blog() {

    }

    public void setOwner(UserDomain user) {
        this.owner = user;
        user.setBlog(this);
    }
}
