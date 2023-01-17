package Likelion.SpringStudy.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="Post")
@Table(name="POST")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name="title")
    @NonNull
    private String title;

    @Column(name="content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name="like_cnt")
    private Integer like_cnt = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BLOG_ID")
    private Blog blog;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(Long id, String title, String content, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
