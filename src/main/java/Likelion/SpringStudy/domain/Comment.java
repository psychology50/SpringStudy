package Likelion.SpringStudy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="Comment")
@Table(name="COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name="content", columnDefinition = "TINYTEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private UserDomain user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public Comment(Long id, String content, Date createdDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
    }
}
