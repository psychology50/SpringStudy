package Likelion.SpringStudy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity(name="Comment")
@Table(name="COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name="content", columnDefinition = "TINYTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserDomain user;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public Comment(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
