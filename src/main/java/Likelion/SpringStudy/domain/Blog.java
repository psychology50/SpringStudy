package Likelion.SpringStudy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="blog")
@Data
@Getter
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

    public Blog() {}

    @Builder
    public Blog(Long id, String blog_name) {
        this.id = id;
        this.blog_name = blog_name;
    }

    public void setOwner(UserDomain user) {
        this.owner = user;
        user.setBlog(this);
    }
}
