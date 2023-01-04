package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name="Like")
@Table(name="Like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserDomain user;

    @Builder
    public Like(Long id, Post post, UserDomain user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }
}
